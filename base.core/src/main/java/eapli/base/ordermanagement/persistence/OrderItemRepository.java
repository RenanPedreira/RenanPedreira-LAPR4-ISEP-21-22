package eapli.base.ordermanagement.persistence;

import eapli.base.ordermanagement.domain.OrderItem;
import eapli.framework.domain.repositories.DomainRepository;

public interface OrderItemRepository extends DomainRepository<Integer, OrderItem> {
}
