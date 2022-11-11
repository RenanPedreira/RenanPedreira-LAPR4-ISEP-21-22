package eapli.base.protocol;

import eapli.base.agvmanagement.application.AGVServerController;
import eapli.base.agvmanagement.application.AGVServerControllerImpl;
import eapli.framework.csv.util.CsvLineMarshaler;
import eapli.framework.util.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.UnknownHostException;
import java.text.ParseException;

@Utility
public class CsvOrderProtocolMessageParser {

    private static final Logger LOGGER = LogManager.getLogger(CsvOrderProtocolMessageParser.class);

    private static AGVServerController controller;



    private CsvOrderProtocolMessageParser() {
        // avoid instantiation
    }

    /**
     * To inject a different controller for testing purposes.
     *
     * @param controller
     */

    /* package */public static void injectController(final AGVServerController controller) {
        synchronized (lock) {
            CsvOrderProtocolMessageParser.controller = controller;
        }
    }

    private static final Object lock = new Object();

    private static AGVServerController getController() throws UnknownHostException {
        synchronized (lock) {
            if (CsvOrderProtocolMessageParser.controller != null) {
                return CsvOrderProtocolMessageParser.controller;
            }
        }
        return new AGVServerControllerImpl();
    }

    /**
     * Parse and build the request.
     *
     * @param inputLine
     *
     * @return
     */
    public static OrderProtocolRequest parse(final String inputLine) {
        // as a fallback make sure we return unknown
        OrderProtocolRequest request = new UnknownRequest(inputLine);

        // parse to determine which type of request and if it is sintactally valid
        String[] tokens;
        try {
            tokens = CsvLineMarshaler.tokenize(inputLine).toArray(new String[0]);

            if ("ADD_CART_ITEM".equals(tokens[0])) {
                System.out.println("ADD_CART_ITEM");
                request = new AddCartItemRequest(inputLine);
            }
            else if ("GET_CART_ITEMS".equals(tokens[0])) {
                System.out.println("GET_CART_ITEMS");
                request = new GetCartItemsRequest(inputLine);
            }
            else if ("COMMTEST".equals(tokens[0])) {
                System.out.println("GET_CART_ITEMS");
                request = new CommtestRequest(inputLine);
            }
            else if ("DISCONN".equals(tokens[0])) {
                System.out.println("GET_CART_ITEMS");
                request = new DisconnRequest(inputLine);
            }
            else if ("GET_CLIENT_ORDERS".equals(tokens[0])) {
                System.out.println("GET_CLIENT_ORDERS");
                request = new GetClientOrdersRequest(inputLine);
            }


        } catch (final ParseException | UnknownHostException e) {
            LOGGER.info("Unable to parse request: {}", inputLine);
            request = new ErrorInRequest(inputLine, "Unable to parse request");
        }

        return request;
    }
        private static boolean isStringParam(final String string) {
        return string.length() >= 2 && string.charAt(0) == '"' && string.charAt(string.length() - 1) == '"';
    }

}
