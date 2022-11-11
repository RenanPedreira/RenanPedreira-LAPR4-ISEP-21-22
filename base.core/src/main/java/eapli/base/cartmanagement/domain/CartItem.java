package eapli.base.cartmanagement.domain;

import eapli.base.cartmanagement.dto.CartItemDTO;
import eapli.base.ordermanagement.domain.Quantity;
import eapli.base.productmanagement.domain.Producto.Product;

import javax.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer CartItemID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product")
    private Product product;

    @Embedded
    private Quantity quantity;

    public CartItem(){}

    public CartItem(Product product, Integer quantity){
        this.product = product;
        this.quantity = new Quantity(quantity);
    }

    public CartItemDTO toDTO(){
        return new CartItemDTO(this.product, this.quantity);
    }

    public Product product(){
        return this.product;
    }
    public Quantity quantity(){return this.quantity;}
}
