package eapli.base.ordermanagement.dto;

import eapli.base.ordermanagement.domain.OrderItem;

public class OrderItemDTO {

    private String product;
    private String quantity;

    public OrderItemDTO(OrderItem orderItem){
        this.product=orderItem.product();
        this.quantity=orderItem.quantity();
    }

    public String showInfo(){
        return String.format("%s\nQuantity{%s}", product, quantity);
    }
}
