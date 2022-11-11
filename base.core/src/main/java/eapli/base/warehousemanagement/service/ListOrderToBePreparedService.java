package eapli.base.warehousemanagement.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Ordre;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.base.ordermanagement.dto.OrderMapper;
import eapli.base.ordermanagement.persistence.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import static eapli.base.ordermanagement.domain.OrderStatus.TOBEPREPARED;

public class ListOrderToBePreparedService {

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    public List<OrderDTO> listOrdersToBePrepared(){
        List<Ordre> orderList = orderRepository.getOrdersByStatus(TOBEPREPARED);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (Ordre order : orderList){
            orderDTOList.add(OrderMapper.toDTO(order));
        }

        return orderDTOList;
    }
    public List<Ordre> listOrdersToBePreparedNormal() {
        List<Ordre> orderList = orderRepository.getOrdersByStatus(TOBEPREPARED);
        return orderList;
    }

}
