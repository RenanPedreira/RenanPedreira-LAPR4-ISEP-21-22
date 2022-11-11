package eapli.base.modules.communications;


import eapli.base.modules.controlUnit.DigitalTwinControlUnit;
import eapli.base.protocol.AgvTwinProtocolRequest;
import eapli.base.modules.controlUnit.DigitalTwinParser;
import eapli.base.shared.SharedMemory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Server socket for agv digital twin daemon using the CSV-based protocol.
 *
 * @author Paulo Gandra Sousa 01/06/2020
 */
public class DigitalTwinServer {

    private static final Logger LOGGER = LogManager.getLogger(DigitalTwinServer.class);

    /**
     * Wait for connections.
     * <p>
     * Suppress warning java:S2189 - Loops should not be infinite
     *
     * @param port
     */
    @SuppressWarnings("java:S2189")
    private void listen(SharedMemory sharedMemory, final int port) {

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

                DigitalTwinControlUnit.AgvTwinHandler handler = new DigitalTwinControlUnit.AgvTwinHandler(clientSocket);
                new Thread(() -> handler.start(sharedMemory));

            } catch (IOException e) {
                LOGGER.info("Failed to receive client socket");
            }
        }
    }

    private void importSSLConfiguration(){
        System.setProperty("javax.net.ssl.keyStore", "SSL/twinserver.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "twinserver");
        System.setProperty("javax.net.ssl.trustStore", "SSL/twinserver.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "twinserver");
    }


    /**
     * Wait for connections.
     *
     * @param port
     * @param blocking if {@code false} the socket runs in its own thread and does not block calling
     *                 thread.
     */
    public void start(SharedMemory sharedMemory, final int port, final boolean blocking) {
        if (blocking) {
            listen(sharedMemory, port);
        } else {
            new Thread(() -> listen(sharedMemory, port)).start();
        }
    }
}


