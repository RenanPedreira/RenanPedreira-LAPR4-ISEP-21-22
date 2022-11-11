package eapli.base.protocol;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.service.AgvSearchService;

import java.net.UnknownHostException;
import java.util.List;

public class AllAgvRequest extends AGVProtocolRequest{

    AgvSearchService service = new AgvSearchService();

    protected AllAgvRequest(String inputRequest) throws UnknownHostException {
        super(inputRequest);
    }

    @Override
    public String execute() {
        return buildResponse(service.allAGV());
    }

    private String buildResponse(final List<AGV> agvs) {
        final var sb = new StringBuilder();
        String result;

        // result rows
        for (final AGV agv : agvs) {
            sb.append("AGV: ");
            sb.append(agv.toDTO().toString());
            sb.append("     ");
        }
        // end of message
        sb.append("\n");

        result = sb.toString();

        return "AGV, \"" + result + "\"\n";
    }
}
