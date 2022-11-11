package eapli.base.protocol;

import java.net.UnknownHostException;

public class DisconnRequest extends AgvTwinProtocolRequest{

    public DisconnRequest(String inputLine) throws UnknownHostException {
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

        return "Agv Server Response: , \"" + result + "\"\n";
    }
}
