package eapli.base.ordermanagement.application;

import eapli.base.ordermanagement.dto.OrderDTO;

import java.util.List;
import java.util.Set;

public class OrderDispatchmentController {
    OrderDispatchmentService service = new OrderDispatchmentService();

    public List<OrderDTO> listAllPreparedOrders() {
        return service.listAllPreparedOrders();
    }

    public void updateStatus(Set<OrderDTO> keySet) {
        service.updateStatusAndPersist(keySet);
    }
}
