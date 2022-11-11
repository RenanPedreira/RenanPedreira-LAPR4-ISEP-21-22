package eapli.base.daemon.order;

import eapli.base.daemon.order.presentation.CsvOrderProtocolServer;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * eCafeteria Booking daemon. Check the Kiosk application for a demo client of this server
 * application.
 *
 * @author Paulo Gandra Sousa 01/06/2020
 */
@SuppressWarnings("squid:S106")
public final class OrderDaemon {

    // TODO read port number from property file
    private static final int BOOKING_PORT = 8880;
    private static final Logger LOGGER = LogManager.getLogger(OrderDaemon.class);
   // private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    /**
     * Avoid instantiation of this class.
     */
    private OrderDaemon() {
    }

    public static void main(final String[] args) throws IOException {
        LOGGER.info("Configuring the daemon");

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(),
                new PlainTextEncoder());

        LOGGER.info("\n\n\n*************************\nStarting the server socket");

        try {
            LOGGER.info("\nSever IP: " + InetAddress.getLocalHost().getHostAddress()+"\nServer Port: "+BOOKING_PORT);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        final var server = new CsvOrderProtocolServer();
        server.start(BOOKING_PORT, true);

        LOGGER.info("Exiting the daemon");
        System.exit(0);
    }
}

