package eapli.base.modules.communications;

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

public class DigitalTwinProxy {
    private static final Logger LOGGER = LogManager.getLogger(DigitalTwinProxy.class);

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
            System.setProperty("javax.net.ssl.keyStore", "SSL/twinclient.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "twinclient");
            System.setProperty("javax.net.ssl.trustStore", "SSL/twinclient.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "twinclient");
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


    public String trim(String str){
        StringBuilder str1 = new StringBuilder(str);
        str1.deleteCharAt(str.length()-1);
        str1.deleteCharAt(0);
        return str1.toString();
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

    private String[] buildRequest2(Integer code, String data){

        //Data values
        String[] request = new String[5];

        Integer len1 = data.length()%256;
        Integer len2=data.length()/256;

        //First 4 values
        request[0] = "1";
        request[1] = String.valueOf(code.byteValue());
        request[2] = String.valueOf(len1.byteValue());
        request[3] = String.valueOf(len2.byteValue());


        request[4] = data.replace(",", "");


        return request;
    }

    private String[] buildRequest3(Integer code, String data1, String data2){

        //Data values
        String[] request = new String[6];

        Integer len1 = data1.length()%256;
        Integer len2=data1.length()/256;

        //First 4 values
        request[0] = "1";
        request[1] = String.valueOf(code.byteValue());
        request[2] = String.valueOf(len1.byteValue());
        request[3] = String.valueOf(len2.byteValue());


        request[4] = data1.replace(",", "");
        request[5] = data2.replace(",", "");

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

    private String convertRequest2(String[] a){
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
        byte[] requestBytes = buildRequest(1, "");
        System.out.println("\nSending Code 1: Disconn Request\n");

        final String request = convertRequest(requestBytes);
        final List<String> response = socket.sendAndRecv(request);
        socket.stop();
        return response.toString();
    }

    public String commtestTwin() throws IOException, FailedRequestException {

        //commtest twin code is 3 and it sends no data
        byte[] requestBytes = buildRequest(3, "");
        System.out.println("\nSending Code 3: Twin Commtest Request\n");

        final String request = convertRequest(requestBytes);
        final List<String> response = socket.sendAndRecv(request);

        return response.toString();
    }

    public String disconnTwin() throws IOException, FailedRequestException {

        //commtest code is 4 and it sends no data
        byte[] requestBytes = buildRequest(4, "");
        System.out.println("\nSending Code 4: Twin Disconn Request\n");

        final String request = convertRequest(requestBytes);
        final List<String> response = socket.sendAndRecv(request);

        return response.toString();
    }

    /** Gets the warehouse's plant(width and length) */
    public String warehousePlantRequest(String warehouse) throws IOException, FailedRequestException {

        //Warehouse plant code is 5 and it sends no data
        String[] requestBytes = buildRequest2(5, warehouse);
        System.out.println("\nSending Code 5: Warehouse Position Request\n");

        final String request = convertRequest2(requestBytes);
        final List<String> response = socket.sendAndRecv(request);

        return response.toString();
    }

    /** Requests the position of AGVs located on the Warehouse*/
    public String allAgvPositionRequest(String warehouse, String agv) throws IOException, FailedRequestException {

        //Other agvs position code is 6 and it sends no data
        String[] requestBytes = buildRequest3(6, warehouse, agv);
        System.out.println("\nSending Code 6: Agvs Position Request\n");

        final String request = convertRequest2(requestBytes);
        final List<String> response = socket.sendAndRecv(request);

        return response.toString();
    }

    /** Gets a list of products location associated to the agv's most recent order */
    public String pickupOrderRequest(String agv) throws IOException, FailedRequestException {

        //Pick-up order code is 7 and it sends no data
        String[] requestBytes = buildRequest2(7, agv);
        System.out.println("\nSending Code 7: Pick-Up Order Request\n");

        final String request = convertRequest2(requestBytes);
        final List<String> response = socket.sendAndRecv(request);

        return response.toString();
    }

}
