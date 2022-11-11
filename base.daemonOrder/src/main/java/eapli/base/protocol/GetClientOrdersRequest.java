package eapli.base.protocol;

import eapli.base.ordermanagement.domain.Ordre;
import eapli.base.service.ListOrdersStatusService;

import java.util.List;

public class GetClientOrdersRequest extends OrderProtocolRequest {

    private ListOrdersStatusService listOrdersStatusService = new ListOrdersStatusService();

    public GetClientOrdersRequest(String inputRequest) {
        super(inputRequest);
    }

    @Override
    public String execute() {
        try {
            final List<Ordre> customerOrders = listOrdersStatusService.getClientOrders(super.tokens[1]);
            return buildResponse(customerOrders);
        } catch (final Exception e) {
            // we should be careful about exposing the Exception to the outside!
            return buildServerError(e.getMessage());
        }
    }

    private String buildResponse(final List<Ordre> orders) {
        final var sb = new StringBuilder();
        String result;

        // header
        sb.append("ORDERS\n");

        // result rows
        for (final Ordre order : orders) {
            sb.append("order id: " + order.numericIdentifier());
            sb.append(", name: " + order.customerName());
            sb.append(", status: " + order.getStatus().name());
            sb.append(", price: " + order.getTotalPrice().toString() + " euros");

            // end of line
            sb.append("\n");
        }
        // end of message
        sb.append("\n");

        result = sb.toString();
        return result;
    }
}