package eapli.base.protocol;


import eapli.base.ordermanagement.application.OrderServerController;
import eapli.framework.csv.util.CsvLineMarshaler;

import java.text.ParseException;

public abstract class OrderProtocolRequest {
    protected final String request;
    protected final String[] tokens;

    protected OrderProtocolRequest(final String inputRequest) {
        this.request = inputRequest;
        try {
            this.tokens = CsvLineMarshaler.tokenize(inputRequest).toArray(new String[0]);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Executes the requested action and builds the response to the client.
     *
     * @return the response to send back to the client
     */
    public abstract String execute();

    /**
     * Indicates the object is a goodbye message, that is, a message that will close the
     * connection to the client.
     *
     * @return {@code true} if the object is a a goodbye message.
     */
    public boolean isGoodbye() {
        return false;
    }

    protected String buildServerError(final String errorDescription) {
        final BaseErrorRequest r = new BaseErrorRequest(request, errorDescription) {

            @Override
            protected String messageType() {
                return "SERVER_ERROR";
            }

        };
        return r.buildResponse();
    }

    protected String buildBadRequest(final String errorDescription) {
        final BaseErrorRequest r = new BaseErrorRequest(request, errorDescription) {

            @Override
            protected String messageType() {
                return "BAD_REQUEST";
            }

        };
        return r.buildResponse();
    }
}
