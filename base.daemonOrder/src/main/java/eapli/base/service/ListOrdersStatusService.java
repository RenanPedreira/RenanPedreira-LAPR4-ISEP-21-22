package eapli.base.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Ordre;
import eapli.base.ordermanagement.persistence.OrderRepository;

import java.util.List;

public class ListOrdersStatusService {

    private final OrderRepository orderRepository = PersistenceContext.repositories().orders();

    /**
     * Get the Client orders by username and which status is not "DELIVERED"
     * @param customerUserName the customer username
     * @return the Client orders
     */
    public List<Ordre> getClientOrders(String customerUserName){
        String withoutDoubleQuotes = customerUserName.replace("\"","");
        List<Ordre> clientOrders = this.orderRepository.getClientOrders(withoutDoubleQuotes);

        return clientOrders;
    }
}
