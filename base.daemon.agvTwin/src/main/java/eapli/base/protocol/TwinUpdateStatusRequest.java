package eapli.base.protocol;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.service.AgvSearchService;

import java.net.UnknownHostException;
import java.util.List;

public class TwinUpdateStatusRequest extends  AgvTwinProtocolRequest{
    AgvSearchService service = new AgvSearchService();

    protected TwinUpdateStatusRequest(String inputRequest) throws UnknownHostException {
        super(inputRequest, null);
    }

    @Override
    public String execute() {
        return buildResponse();
    }

    private String buildResponse() {
        final var sb = new StringBuilder();
        String result;

        // result rows
        sb.append("AGV status updated ");

        // end of message
        sb.append("\n");

        result = sb.toString();

        return "Twin_Response, \"" + result + "\"\n";
    }

}
