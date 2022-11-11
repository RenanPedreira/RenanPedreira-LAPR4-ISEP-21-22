package eapli.base.protocol;

import eapli.base.cartmanagement.domain.CartItem;
import eapli.base.cartmanagement.dto.CartItemDTO;
import eapli.base.service.AddProductToCartService;
import eapli.framework.csv.CsvRecord;

public class GetCartItemsRequest extends OrderProtocolRequest{

    AddProductToCartService service = new AddProductToCartService();

    public GetCartItemsRequest(String inputRequest){
        super(inputRequest);
    }

    @Override
    public String execute() {
        return buildResponse(service.cartCurrentProducts(super.tokens[1]));
    }

    private String buildResponse(final Iterable<CartItem> items) {
        final var sb = new StringBuilder();
        String result;

        // result rows
        for (final CartItem item : items) {
            sb.append("Product: ");
            sb.append(item.product().name().toString());
            sb.append(" | Quantity: ");
            sb.append(item.quantity().toString());
            sb.append("     ");
        }
        // end of message
        sb.append("\n");

        result = sb.toString();

        return "ITEMS, \"" + result + "\"\n";
    }

}