package eapli.base.protocol;

import java.net.UnknownHostException;

public class CommtestRequest extends AgvTwinProtocolRequest{

    public CommtestRequest(String inputLine) throws UnknownHostException {
        super(inputLine, null);
    }

    @Override
    public String execute(){
        return buildResponse();
    }

    public String buildResponse(){
        final var sb = new StringBuilder();
        String result;

        // result rows
        sb.append("ACK");
        // end of message
        sb.append("\n");

        result = sb.toString();

        return "Digital Twin Response: , \"" + result + "\"\n";
    }

}
