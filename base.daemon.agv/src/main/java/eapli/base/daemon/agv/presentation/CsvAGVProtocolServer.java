package eapli.base.daemon.agv.presentation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import eapli.base.protocol.AGVProtocolRequest;
import eapli.base.protocol.CsvAGVProtocolMessageParser;
import eapli.base.service.AutoFifoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 * Server socket for booking daemon using the CSV-based protocol.
 *
 * @author Paulo Gandra Sousa 01/06/2020
 */
public class CsvAGVProtocolServer {

    private static final Logger LOGGER = LogManager.getLogger(CsvAGVProtocolServer.class);

    /**
     * Client socket.
     *
     * @author Paulo Gandra Sousa 01/06/2020
     */
    private static class AgvHandler extends Thread {
        private Socket agvSocket;

        public AgvHandler(final Socket socket) {
            this.agvSocket = socket;
        }

        @Override
        public void run() {

            final var clientIP = agvSocket.getInetAddress();
            LOGGER.debug("Accepted connection from {}:{}", clientIP.getHostAddress(), agvSocket.getPort());

            try (var out = new PrintWriter(agvSocket.getOutputStream(), true);
                 var in = new BufferedReader(new InputStreamReader(agvSocket.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    LOGGER.debug("Received message:----\n{}\n----", inputLine);

                    final AGVProtocolRequest request = CsvAGVProtocolMessageParser.parse(inputLine);
                    final String response = request.execute();

                    out.println(response);
                    LOGGER.debug("Sent message:----\n{}\n----", response);
                    if (request.isGoodbye()) {
                        break;
                    }
                    if (request.isGoodbye()) {
                        break;
                    }
                }
            }catch (final IOException e) {
                LOGGER.error(e);
            } finally {
                try {
                    agvSocket.close();
                    LOGGER.debug("Closing client socket {}:{}", clientIP.getHostAddress(), agvSocket.getPort());
                } catch (final IOException e) {
                    LOGGER.error("While closing the client socket {}:{}", clientIP.getHostAddress(),
                            agvSocket.getPort(), e);
                }
                // null the reference to ensure it will be caught by the garbage collector
                agvSocket = null;

            }
        }
    }

    private static class FifoHandler extends Thread {
        @Override
        public void run(){
            LOGGER.debug("Running the automatic FIFO");
            AutoFifoService fifoRunner = new AutoFifoService();
            fifoRunner.runFifo();
        }

    }

    /**
     * Wait for connections.
     * <p>
     * Suppress warning java:S2189 - Loops should not be infinite
     *
     * @param port
     */
    @SuppressWarnings("java:S2189")
    private void listen2(final int port) {

        try (var serverSocket = new ServerSocket(port)) {
            while (true) {
                final var agvSocket = serverSocket.accept();
                new AgvHandler(agvSocket).start();
            }
        } catch (final IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Wait for connections.
     * <p>
     * Suppress warning java:S2189 - Loops should not be infinite
     *
     * @param port
     */
    private void listen(final int port) {

        importSSLConfiguration();

        SSLServerSocket serverSocket = null;
        Socket clientSocket;

        SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try {
            serverSocket = (SSLServerSocket) factory.createServerSocket(port);
            serverSocket.setNeedClientAuth(true);
        } catch (IOException e) {
            LOGGER.info("Failed to create server socket");
            System.exit(1);
        }

        while(true) {
            try {
                clientSocket = serverSocket.accept();
                new AgvHandler(clientSocket).start();
            } catch (IOException e) {
                LOGGER.info("Failed to receive client socket");
            }
        }
    }

    private void fifo(){
        new FifoHandler().start();
    }

    /**
     * Wait for connections.
     *
     * @param port
     * @param blocking if {@code false} the socket runs in its own thread and does not block calling
     *                 thread.
     */
    public void start(final int port, final boolean blocking) {
        importSSLConfiguration();
        //new Thread(() -> fifo()).start();

        if (blocking) {
            listen(port);
        } else {
            new Thread(() -> listen(port)).start();
        }
    }

    private void importSSLConfiguration(){
        System.setProperty("javax.net.ssl.keyStore", "SSL/agvmanager.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "agvmanager");
        System.setProperty("javax.net.ssl.trustStore", "SSL/agvmanager.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "agvmanager");
    }


}


