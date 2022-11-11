package eapli.base.ordermanagement.persistence;

import eapli.base.ordermanagement.domain.OrderNumericIdentifier;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.Ordre;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface OrderRepository extends DomainRepository <OrderNumericIdentifier, Ordre> {
    Iterable<Ordre> findAllPreparedOrders();
    Iterable<Ordre> findAllDispatchedOrders();
    List<Ordre> getOrdersByStatus(OrderStatus status);
    List<Ordre> getClientOrders(String customer);
}
