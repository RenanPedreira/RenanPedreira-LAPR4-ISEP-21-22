package eapli.base.protocol;

import eapli.framework.csv.CsvRecord;

import java.net.UnknownHostException;

public abstract class BaseErrorRequest extends AgvTwinProtocolRequest {

    private final String errorDescription;

    protected BaseErrorRequest(final String request, final String errorDescription) throws UnknownHostException {
        super(request, null);
        this.errorDescription = errorDescription;
    }

    protected BaseErrorRequest(final String request) throws UnknownHostException {
        super(request, null);
        this.errorDescription = null;
    }

    @Override
    public String execute() {
        // nothing to do, just build the response
        return buildResponse();
    }

    protected String buildResponse() {
        final Object[] fields = {
                messageType(),
                request,
                errorDescription
        };
        final boolean[] mask = { false, true, true };
        return CsvRecord.valueOf(fields, mask).toString() + "\n";
    }

    protected abstract String messageType();
}
