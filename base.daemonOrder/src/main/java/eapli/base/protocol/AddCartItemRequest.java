package eapli.base.protocol;

import eapli.base.service.AddProductToCartService;


public class AddCartItemRequest extends OrderProtocolRequest{

    AddProductToCartService service = new AddProductToCartService();

    protected AddCartItemRequest(String inputRequest) {
        super(inputRequest);
    }

    @Override
    public String execute() {
        return service.addProductToCart(super.tokens[1], super.tokens[2], super.tokens[3]);
    }
}
