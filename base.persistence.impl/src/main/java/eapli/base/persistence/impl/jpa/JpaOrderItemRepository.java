package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.ordermanagement.persistence.OrderItemRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;
import java.util.Optional;

public class JpaOrderItemRepository
        extends JpaAutoTxRepository<OrderItem, Long, Long>
        implements OrderItemRepository {

    public JpaOrderItemRepository(TransactionalContext autoTx){
        super(autoTx, "orderID");
    }

    public JpaOrderItemRepository(String puname, Map extendedPersistenceProperties){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "orderID");
    }

    @Override
    public Optional<OrderItem> ofIdentity(Integer id) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(Integer entityId) {

    }
}
