package eapli.base.daemon.agvTwin;

import eapli.base.modules.communications.DigitalTwinServer;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * eCafeteria Booking daemon. Check the Kiosk application for a demo client of this server
 * application.
 *
 * @author Paulo Gandra Sousa 01/06/2020
 */
@SuppressWarnings("squid:S106")
public final class AgvTwinDaemon {

    // TODO read port number from property file
    private static final int PORT = 8870;
    private static final Logger LOGGER = LogManager.getLogger(AgvTwinDaemon.class);

    /**
     * Avoid instantiation of this class.
     */
    private AgvTwinDaemon() {
    }

    public static void main(final String[] args) {
        LOGGER.info("Configuring the daemon");

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(),
                new PlainTextEncoder());

        LOGGER.info("\n\n\n*************************\nStarting the server socket");

        try {
            LOGGER.info("\nSever IP: " + InetAddress.getLocalHost().getHostAddress()+"\nServer Port: "+PORT);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        final var server = new DigitalTwinServer();
        server.start(null, PORT, true);

        LOGGER.info("Exiting the daemon");
        System.exit(0);
    }
}

