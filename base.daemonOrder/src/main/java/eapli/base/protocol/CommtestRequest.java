package eapli.base.protocol;

import java.net.UnknownHostException;

public class CommtestRequest extends OrderProtocolRequest{

    public CommtestRequest(String inputLine) throws UnknownHostException {
        super(inputLine);
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

        return "Order Server Response: , \"" + result + "\"\n";
    }

}
