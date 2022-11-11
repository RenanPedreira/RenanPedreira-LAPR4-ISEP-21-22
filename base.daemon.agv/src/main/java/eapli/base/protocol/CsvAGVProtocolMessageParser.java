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
public class CsvAGVProtocolMessageParser {

    private static final Logger LOGGER = LogManager.getLogger(CsvAGVProtocolMessageParser.class);


    private CsvAGVProtocolMessageParser() {
        // avoid instantiation
    }

    /**
     * Parse and build the request.
     *
     * @param inputLine
     *
     * @return
     */
    public static AGVProtocolRequest parse(final String inputLine) throws UnknownHostException {
        // as a fallback make sure we return unknown
        AGVProtocolRequest request = new UnknownRequest(inputLine);

        // parse to determine which type of request
        String[] tokensAlter;
        try {
            tokensAlter = inputLine.split(",");

            if ("0".equals(tokensAlter[1]))
                request = new CommtestRequest(inputLine);
            else if ("1".equals(tokensAlter[1]))
                request = new DisconnRequest(inputLine);
            else if ("3".equals(tokensAlter[1]))
                request = new CommtestTwinRequest(inputLine);
            else if ("4".equals(tokensAlter[1]))
                request = new DisconnTwinRequest(inputLine);
            else if ("5".equals(tokensAlter[1]))
                request = new WarehousePlantRequest(inputLine);
            else if ("6".equals(tokensAlter[1]))
                request = new AgvsCurrentLocationRequest(inputLine);
            else if ("7".equals(tokensAlter[1]))
                request = new PickupOrderRequest(inputLine);

        } catch (final UnknownHostException e) {
            LOGGER.info("Unable to parse request: {}", inputLine);
            request = new ErrorInRequest(inputLine, "Unable to parse request");
        }

        return request;
    }

}
