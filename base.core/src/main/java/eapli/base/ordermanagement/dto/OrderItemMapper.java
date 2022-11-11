package eapli.base.ordermanagement.dto;

import eapli.base.ordermanagement.domain.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemMapper {

    public List<OrderItemDTO> toDTO(List<OrderItem> orderItem){
        List<OrderItemDTO> list = new ArrayList<>();
        for(OrderItem item : orderItem){
            OrderItemDTO itemDTO = new OrderItemDTO(item);
            list.add(itemDTO);
        }
        return list;
    }
}
