package eapli.base.agvManagerAPI;

import org.junit.Test;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class AgvManagerBackofficeProxy {

    private InetAddress serverIP;
    private Integer port=8890;
    private SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

    private SSLSocket sock;

    /**
     * Tests that it's not possible to connect when the client doesn't know the server and the server doesn't know the client
     */
    @Test(expected = java.lang.RuntimeException.class)
    public void testInvalidSSL(){

        /** Gets keystore and truststore properties that are not allowed to communicate with the AGV Manger */
        System.setProperty("javax.net.ssl.keyStore", "SSL/fakeclient.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "fakeclient");
        System.setProperty("javax.net.ssl.trustStore", "SSL/fakeclient.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "fakeclient");

        /** Gets the AgvManager's ip */
        try {
            serverIP = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        /** Starts a SSLSocket to try to establish a connection with the AGV Manager */
        try {
            sock = (SSLSocket) sf.createSocket(serverIP, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /** Tries to start a connection */
        try {
            sock.startHandshake();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Tests that it's not possible to connect when the client doesn't know the server but the server knows the client
     */
    @Test(expected = java.lang.RuntimeException.class)
    public void testInvalidSSL2(){

        /** Gets keystore and truststore properties that are not allowed to communicate with the AGV Manger */
        System.setProperty("javax.net.ssl.keyStore", "SSL/backoffice.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "backoffice");
        System.setProperty("javax.net.ssl.trustStore", "SSL/fakeclient.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "fakeclient");

        /** Gets the AgvManager's ip */
        try {
            serverIP = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        /** Starts a SSLSocket to try to establish a connection with the AGV Manager */
        try {
            sock = (SSLSocket) sf.createSocket(serverIP, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /** Tries to start a connection */
        try {
            sock.startHandshake();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Tests that it's not possible to connect when the client knows the server but the server doesn't know the client
     */
    @Test(expected = java.lang.RuntimeException.class)
    public void testInvalidSSL3(){

        /** Gets keystore and truststore properties that are not allowed to communicate with the AGV Manger */
        System.setProperty("javax.net.ssl.keyStore", "SSL/fakeclient.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "fakeclient");
        System.setProperty("javax.net.ssl.trustStore", "SSL/backoffice.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "backoffice");

        /** Gets the AgvManager's ip */
        try {
            serverIP = InetAddress.getByName(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        /** Starts a SSLSocket to try to establish a connection with the AGV Manager */
        try {
            sock = (SSLSocket) sf.createSocket(serverIP, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /** Tries to start a connection */
        try {
            sock.startHandshake();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
