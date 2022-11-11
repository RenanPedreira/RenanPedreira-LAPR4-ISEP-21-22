package eapli.base.ordermanagement.domain;

import eapli.base.productmanagement.domain.Producto.Price;
import eapli.base.productmanagement.domain.Producto.Product;
import eapli.base.productmanagement.domain.Producto.ShortDescription;
import eapli.framework.domain.model.DomainFactory;

/**
 *
 * @author Renan Pedreira
 */
public class OrderItemBuilder implements DomainFactory<OrderItem> {

    private OrderItem orderItem;
    private Product product;
    private Quantity quantity;

    public OrderItemBuilder orderItem(final OrderItem orderItem){
        this.orderItem = orderItem;
        return this;
    }

    public OrderItemBuilder product(final Product product){
        this.product = product;
        return this;
    }

    public OrderItemBuilder quantity(final Quantity quantity){
        this.quantity = quantity;
        return this;
    }


    private OrderItem buildOrThrow(){
        if (orderItem != null){
            return orderItem;
        }else if(product != null && quantity != null) {
            orderItem = new OrderItem(product, quantity);
            return orderItem;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public OrderItem build() {
        final OrderItem orderItem = buildOrThrow();
        return orderItem;
    }
}
