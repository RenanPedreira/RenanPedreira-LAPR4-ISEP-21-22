package eapli.base.orderServerAPI;

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

public class CsvOrderProtocolProxy {
    private static final Logger LOGGER = LogManager.getLogger(CsvOrderProtocolProxy.class);

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
        return 8880;
    }

    private String getAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private String removeDoubleQuotes(final String token) {
        return token.replace("\"", "").trim();
    }

    private void checkForErrorMessage(final List<String> response) throws FailedRequestException {
        final String[] tokens = response.get(0).split(",");
        final String messageType = tokens[0];

        if (messageType.equals("SERVER_ERROR")
                || messageType.equals("BAD_REQUEST")
                || messageType.equals("UNKNOWN_REQUEST")
                || messageType.equals("ERROR_IN_REQUEST")) {
            throw new FailedRequestException();
        }
    }

    //Connects to the Server
    public boolean connectOrderServer(){
        socket = new CsvOrderProtocolProxy.ClientSocket();
        try {
            socket.connect(getAddress(), getPort());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    //Disconnects from Server
    public boolean disconnectOrderServer(){
        try {
            socket.stop();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    //ADD CART ITEM
    public void addCartItem(final String email, final String productDTO, final String quantity) throws IOException, FailedRequestException {
        //final var socket = new ClientSocket();
        //socket.connect(getAddress(), getPort());

        final String request = buildAddCartItem(email, productDTO, quantity);
        final List<String> response = this.socket.sendAndRecv(request);

        //socket.stop();
    }

    private String buildAddCartItem(final String email, final String productDTO, final String quantity) {
        return "ADD_CART_ITEM, \"" + email + "\",\"" + productDTO + "\",\"" + quantity + "\"";
    }

    //SEE CART ITEMS
    public String cartItems(final String email) throws IOException, FailedRequestException {
        //final var socket = new ClientSocket();
        //socket.connect(getAddress(), getPort());

        final String request = buildCartItems(email);
        final List<String> response = this.socket.sendAndRecv(request);

        //socket.stop();

        return response.toString();
    }

    private String buildCartItems(final String email) {
        return "GET_CART_ITEMS, \"" + email + "\"";
    }


    //SPOMSP Commtest
    public String commtest() throws IOException, FailedRequestException {

        final String request = buildAllCommtest();
        final List<String> response = socket.sendAndRecv(request);

        return response.toString();
    }
    private String buildAllCommtest() {
        return "COMMTEST, \"" + "test" + "\"";
    }

    //SPOMSP Disconn
    public String disconn() throws IOException, FailedRequestException {

        final String request = buildAllDisconn();
        final List<String> response = socket.sendAndRecv(request);

        socket.stop();

        return response.toString();
    }
    private String buildAllDisconn() {
        return "DISCONN, \"" + "disconnect" + "\"";
    }

    public String getClientOrders(final String customer)throws IOException {

        final String request = buildGetClientOrders(customer);
        final List<String> response = socket.sendAndRecv(request);

        return response.toString();
    }

    private String buildGetClientOrders(final String customer) {
        return "GET_CLIENT_ORDERS, \"" + customer + "\"";
    }
}
