package eapli.base.ordermanagement.application;

import eapli.base.ordermanagement.dto.OrderDTO;

import java.util.List;
import java.util.Set;

public class OrderDeliveredController {

    OrderDeliveredService service = new OrderDeliveredService();

    public List<OrderDTO> listAllDispatchedOrders() {return service.listAllDispatchedOrders();}

    public void updateStatus (Set<OrderDTO> keySet) { service.updateStatusAndPersist(keySet);}






}
