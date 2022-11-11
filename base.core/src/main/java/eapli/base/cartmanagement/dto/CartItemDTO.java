package eapli.base.cartmanagement.dto;

import eapli.base.cartmanagement.domain.CartItem;
import eapli.base.ordermanagement.domain.Quantity;
import eapli.base.productmanagement.domain.Producto.Product;
import eapli.base.productmanagement.dto.ProductDTO;

public class CartItemDTO {

    private String product;
    private String quantity;

    public CartItemDTO(Product product, Quantity quantity){
        this.product = product.toDTO().showInformation();
        this.quantity = quantity.toString();
    }

    public CartItemDTO(ProductDTO product, String quantity){
        this.product =product.showInformation();
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("\nProduct:\n%s\n\nQuantity: %s", this.product, this.quantity);
    }
}
