package eapli.base.ordermanagement.domain;

import eapli.base.productmanagement.domain.Producto.Price;

import eapli.base.productmanagement.domain.Producto.Product;
import eapli.base.productmanagement.domain.Producto.ShortDescription;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

/**
 *
 * @author Renan Pedreira
 */
@Entity
public class OrderItem implements AggregateRoot<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderID;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product")
    private Product product;

    /*
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vladomiro")
    private Ordre order;
    */

    @Embedded
    private Quantity quantity;

    @Embedded
    private Price price;

    @Embedded
    private ShortDescription description;

    public OrderItem(){}

    public OrderItem(Product product, Quantity quantity){
        this.product=product;
        this.quantity=quantity;
        this.price=new Price(Double.parseDouble(product.getPrice()));
        this.description=new ShortDescription(product.getShortDescription());
    }

    @Override
    public String toString() {
        return String.format("Product: %s" +
                "Quantity: %s" +
                "Price: %s" +
                "Description: %s", product.getName(), quantity.toString(), price.toString(), description.toString());
    }

    public Product getProduct() {
        return product;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public Price getPrice() {
        return price;
    }

    public ShortDescription getDescription() {
        return description;
    }

    public String product(){
        return this.product.toString();
    }

    public String quantity(){
        return this.quantity.toString();
    }

    public String price(){return this.price.toString();}


    @Override
    public boolean sameAs(Object other) {
        return this.equals((OrderItem)other);
    }

    @Override
    public Integer identity() {
        return this.orderID;
    }
}
