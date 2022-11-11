package eapli.base.protocol;

import java.net.UnknownHostException;

public class UnknownRequest extends BaseErrorRequest {

    public UnknownRequest(final String inputLine) throws UnknownHostException {
        super(inputLine);
    }

    @Override
    protected String messageType() {
        return "UNKNOWN_REQUEST";
    }
}
