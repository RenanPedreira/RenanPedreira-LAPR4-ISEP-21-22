package eapli.base.daemon.agvTwin.application;

import eapli.base.orderServerAPI.FailedRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class CsvDigitalTwinProtocolProxy {
    private static final Logger LOGGER = LogManager.getLogger(CsvDigitalTwinProtocolProxy.class);

    private static class ClientSocket {

        private Socket sock;
        private PrintWriter output;
        private BufferedReader input;

        /**
         * @param address
         * @param port
         *
         * @throws IOException
         */
        public void connect(final String address, final int port) throws IOException {
            InetAddress serverIP;

            serverIP = InetAddress.getByName(address);

            sock = new Socket(serverIP, port);
            output = new PrintWriter(sock.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            LOGGER.debug("Connected to {}", address);
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

    //SearchAgv
    public String searchAgv()
            throws IOException, FailedRequestException {
        final var socket1 = new ClientSocket();
        socket1.connect(getAddress(), getPort());

        final String request = buildAllAgv1();
        final List<String> response = socket1.sendAndRecv(request);

        socket1.stop();

        return response.toString();
    }

    private String buildAllAgv1() {
        return "ALL_AGV, \"" + "all" + "\"";
    }


    //Update status
    public String updateStatus(String agv, String status)
            throws IOException, FailedRequestException {
        final var socket2 = new ClientSocket();
        socket2.connect(getAddress(), getPort());

        final String request = buildAllAgv2(agv, status);
        final List<String> response = socket2.sendAndRecv(request);

        socket2.stop();

        return response.toString();
    }

    private String buildAllAgv2(String agv, String status) {
        return "UPDATE_STATUS_MANAGER, \"" + agv + "\"" + status + "\"";
    }

}
