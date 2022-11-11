package eapli.base.ordermanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.OrderNumericIdentifier;
import eapli.base.ordermanagement.domain.Ordre;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.base.ordermanagement.dto.OrderMapper;
import eapli.base.ordermanagement.persistence.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderDispatchmentService {
    private OrderRepository repository = PersistenceContext.repositories().orders();

    public List<OrderDTO> listAllPreparedOrders() {
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (Ordre order : repository.findAllPreparedOrders())
            orderDTOList.add(OrderMapper.toDTO(order));

        return orderDTOList;
    }

    public void updateStatusAndPersist(Set<OrderDTO> orderDTOSet) {
        for (OrderDTO orderDTO : orderDTOSet) {
            Ordre order = repository.ofIdentity(new OrderNumericIdentifier(orderDTO.getNumericIdentifier())).get();

            order.changeStatusToDispatchment();
            repository.save(order);
        }
    }
}
