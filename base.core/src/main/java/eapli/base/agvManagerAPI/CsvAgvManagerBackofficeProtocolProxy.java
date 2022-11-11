package eapli.base.agvManagerAPI;

import eapli.base.orderServerAPI.FailedRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class CsvAgvManagerBackofficeProtocolProxy {
    private static final Logger LOGGER = LogManager.getLogger(CsvAgvManagerBackofficeProtocolProxy.class);

    private static ClientSocket socket;

    private static class ClientSocket {

        private SSLSocket sock;
        private PrintWriter output;
        private BufferedReader input;

        /**
         * @param address
         * @param port
         *
         * @throws IOException
         */
        public void connect(final String address, final int port) throws IOException {

            importSSLConfiguration();

            SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

            InetAddress serverIP = InetAddress.getByName(address);

            sock = (SSLSocket) sf.createSocket(serverIP, port);
            sock.startHandshake();
            output = new PrintWriter(sock.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            LOGGER.debug("Connected to {}", address);
        }

        private void importSSLConfiguration(){
            System.setProperty("javax.net.ssl.keyStore", "SSL/backoffice.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "backoffice");
            System.setProperty("javax.net.ssl.trustStore", "SSL/backoffice.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "backoffice");
        }

        /**
         * @param request
         */
        public void send(final String request) {
            output.println(request);
            LOGGER.debug("Sent message\n-----\n{}\n-----", request);
        }


        public List<String> recv() throws IOException {
            final var resp = new ArrayList<String>();

            var eof = false;
            do {
                final String inputLine = input.readLine();
                if (inputLine != null) {
                    if (inputLine.equals("")) {
                        eof = true;
                    } else {
                        resp.add(inputLine);
                    }
                }
            } while (!eof);

            LOGGER.debug("Received message:\n----\n{}\n----", resp);

            return resp;
        }

        /**
         * @param request
         *
         * @return
         *
         * @throws IOException
         */
        public List<String> sendAndRecv(final String request) throws IOException {
            send(request);
            return recv();
        }

        /**
         * @throws IOException
         */
        public void stop() throws IOException {
            input.close();
            output.close();
            sock.close();
        }
    }

    private int getPort() {
        return 8890;
    }

    private String getAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    //Connects to the Server
    public boolean connectAgvManagerServer(){

        socket = new ClientSocket();
        try {

            socket.connect(getAddress(), getPort());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    //Disconnects from Server
    public boolean disconnectAgvManagerServer(){
        try {
            socket.stop();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    //Request Building
    private byte[] buildRequest(Integer code, String data){

        //Data values
        byte[] dataBytes = data.getBytes();

        byte[] request = new byte[4+dataBytes.length];

        Integer len1 = data.length()%256;
        Integer len2=data.length()/256;

        //First 4 values
        request[0] = 1;
        request[1] = code.byteValue();
        request[2] = len1.byteValue();
        request[3] = len2.byteValue();

        for(int i =0; i<data.getBytes().length; i++){
            request[4+i] = dataBytes[i];
        }

        return request;
    }

    //
    private String convertRequest(byte[] a){
        StringBuilder request=new StringBuilder();

        for(int i=0; i<a.length; i++){
            request.append(a[i]);
            request.append(",");
        }

        return request.toString();
    }

    public String commtestRequest() throws IOException, FailedRequestException {

        //commtest code is 0 and it sends no data
        byte[] requestBytes = buildRequest(0, "");
        System.out.println("\nSending Code 0: Commtest Request\n");

        final String request = convertRequest(requestBytes);
        final List<String> response = socket.sendAndRecv(request);

        return response.toString();
    }

    public String disconnRequest() throws IOException, FailedRequestException {

        //disconn code is 1 and it sends no data
        byte[] requestBytes = buildRequest(1, "Disconnect");
        System.out.println("\nSending Code 1: Disconn Request\n");

        final String request = convertRequest(requestBytes);
        final List<String> response = socket.sendAndRecv(request);
        socket.stop();
        return response.toString();
    }
}
