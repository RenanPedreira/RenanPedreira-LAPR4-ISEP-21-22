package eapli.base.cartmanagement.domain;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.productmanagement.domain.Producto.Product;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart implements AggregateRoot<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> productList = new ArrayList<>();

    public Cart(){}

    public Cart(Customer customer){
        this.customer = customer;
    }

    public void addProductToCart(Product product, Integer quantity){
        productList.add(new CartItem(product, quantity));
    }

    public List<CartItem> items(){
        return this.productList;
    }

    @Override
    public boolean sameAs(Object other) {
        return this.equals(other);
    }

    @Override
    public Integer identity() {
        return this.id;
    }
}
