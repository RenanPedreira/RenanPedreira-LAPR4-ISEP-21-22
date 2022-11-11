package eapli.base.daemon.order.presentation;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


import eapli.base.protocol.CsvOrderProtocolMessageParser;
import eapli.base.protocol.OrderProtocolRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;


/**
 * Server socket for booking daemon using the CSV-based protocol.
 *
 * @author Paulo Gandra Sousa 01/06/2020
 */
public class CsvOrderProtocolServer {

    private static final Logger LOGGER = LogManager.getLogger(CsvOrderProtocolServer.class);

    /**
     * Client socket.
     *
     * @author Paulo Gandra Sousa 01/06/2020
     */
    private static class OrderHandler extends Thread {
        private Socket orderSocket;

        public OrderHandler(final Socket socket) {
            this.orderSocket = socket;
        }

        @Override
        public void run() {
            final var clientIP = orderSocket.getInetAddress();
            final int port = orderSocket.getPort();
            LOGGER.debug("Accepted connection from {}:{}", clientIP.getHostAddress(), port);

            try (var out = new PrintWriter(orderSocket.getOutputStream(), true);
                 var in = new BufferedReader(new InputStreamReader(orderSocket.getInputStream()))) {
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    LOGGER.debug("Received message:----\n{}\n----", inputLine);
                    final OrderProtocolRequest request = CsvOrderProtocolMessageParser.parse(inputLine);
                    final String response = request.execute(); //IMPORTANT
                    out.println(response);
                    LOGGER.debug("Sent message:----\n{}\n----", response);
                    if (request.isGoodbye()) {
                        break;
                    }
                }
            }catch (final IOException e) {
                LOGGER.error(e);
            } finally {
                try {
                    orderSocket.close();
                    LOGGER.debug("Closing client socket {}:{}", clientIP.getHostAddress(), port);
                } catch (final IOException e) {
                    LOGGER.error("While closing the client socket {}:{}", clientIP.getHostAddress(),
                            orderSocket.getPort(), e);
                }
                // null the reference to ensure it will be caught by the garbage collector
                orderSocket = null;

                // helper debug - SHOULD NOT BE USED IN PRODUCTION CODE!!!
                if (LOGGER.isDebugEnabled()) {
                    final int finalThreadCount = Thread.activeCount();
                    LOGGER.debug("Ending client thread - final thread count: {}", finalThreadCount);
                    final Thread[] t = new Thread[finalThreadCount];
                    final int n = Thread.enumerate(t);
                    for (var i = 0; i < n; i++) {
                        LOGGER.debug("T {}: {}", t[i].getId(), t[i].getName());
                    }
                }
            }
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
    private void listen(final int port) throws IOException {

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
            clientSocket = serverSocket.accept();
            new Thread(new  OrderHandler(clientSocket)).start();
        }
    }


    /**
     * Wait for connections.
     *
     * @param port
     * @param blocking if {@code false} the socket runs in its own thread and does not block calling
     *                 thread.
     */
    public void start(final int port, final boolean blocking) throws IOException {
        importSSLConfiguration();
        listen(port);
    }

    private void importSSLConfiguration(){
        System.setProperty("javax.net.ssl.keyStore", "SSL/orderserver.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "orderserver");
        System.setProperty("javax.net.ssl.trustStore", "SSL/orderserver.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "orderserver");
    }

}