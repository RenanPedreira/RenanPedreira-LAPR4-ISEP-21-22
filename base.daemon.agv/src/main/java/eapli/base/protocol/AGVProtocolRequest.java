package eapli.base.protocol;

import eapli.base.agvmanagement.application.AGVServerController;
import eapli.base.agvmanagement.application.AGVServerControllerImpl;
import eapli.framework.csv.util.CsvLineMarshaler;

import java.net.UnknownHostException;
import java.text.ParseException;

public abstract class AGVProtocolRequest {
    protected final String request;
    protected final String[] tokens;

    protected AGVProtocolRequest(final String inputRequest) throws UnknownHostException {
        this.request = inputRequest;
        this.tokens = inputRequest.split(",");
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

    protected String buildServerError(final String errorDescription) throws UnknownHostException {
        final BaseErrorRequest r = new BaseErrorRequest(request, errorDescription) {

            @Override
            protected String messageType() {
                return "SERVER_ERROR";
            }

        };
        return r.buildResponse();
    }

    protected String buildBadRequest(final String errorDescription) throws UnknownHostException {
        final BaseErrorRequest r = new BaseErrorRequest(request, errorDescription) {

            @Override
            protected String messageType() {
                return "BAD_REQUEST";
            }

        };
        return r.buildResponse();
    }
}
