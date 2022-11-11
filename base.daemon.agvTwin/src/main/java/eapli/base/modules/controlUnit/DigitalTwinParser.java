package eapli.base.modules.controlUnit;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.protocol.*;
import eapli.base.shared.SharedMemory;
import eapli.framework.csv.util.CsvLineMarshaler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.UnknownHostException;
import java.text.ParseException;

public class DigitalTwinParser {

    private static final Logger LOGGER = LogManager.getLogger(DigitalTwinParser.class);

    private static SharedMemory sharedMemory;

    public DigitalTwinParser(SharedMemory sharedMemory) {
        this.sharedMemory=sharedMemory;
    }


    /**
     * Parse and build the request.
     *
     * @param inputLine
     * @return
     */
    public static AgvTwinProtocolRequest parse(final String inputLine) throws UnknownHostException {
        // as a fallback make sure we return unknown
        AgvTwinProtocolRequest request= new UnknownRequest(inputLine);
        String[] tokens;

        try {
            tokens = CsvLineMarshaler.tokenize(inputLine).toArray(new String[0]);

            if ("0".equals(tokens[0]))
                request = new CommtestRequest(inputLine);
            else if ("1".equals(tokens[0]))
                request = new DisconnRequest(inputLine);
            else if("3".equals(tokens[0]))
                request = new CurrentPositionRequest(inputLine, sharedMemory);
            else if("4".equals(tokens[0]))
                request = new CurrentStatusRequest(inputLine, sharedMemory);

        } catch (final ParseException | UnknownHostException e) {
            LOGGER.info("Unable to parse request: {}", inputLine);
            request = new ErrorInRequest(inputLine, "Unable to parse request");
        }

        return request;
    }
}
