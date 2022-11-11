package eapli.base.protocol;

import java.net.UnknownHostException;

public class BadRequest extends BaseErrorRequest {

    public BadRequest(final String request, final String errorDescription) throws UnknownHostException {
        super(request, errorDescription);
    }

    @Override
    protected String messageType() {
        return "ERROR_IN_REQUEST";
    }
}
