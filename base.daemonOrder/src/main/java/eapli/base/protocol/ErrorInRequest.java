package eapli.base.protocol;

public class ErrorInRequest extends BaseErrorRequest {

    public ErrorInRequest(final String request, final String errorDescription) {
        super(request, errorDescription);
    }

    @Override
    protected String messageType() {
        return "ERROR_IN_REQUEST";
    }
}