package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordermanagement.domain.OrderNumericIdentifier;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.domain.Ordre;
import eapli.base.ordermanagement.persistence.OrderRepository;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static eapli.base.ordermanagement.domain.OrderStatus.DELIVERED;

public class JpaOrderRepository
        extends JpaAutoTxRepository<Ordre, OrderNumericIdentifier, OrderNumericIdentifier>
        implements OrderRepository {

    public JpaOrderRepository(TransactionalContext autoTx){
        super(autoTx, "OrderNumericIdentifier");
    }

    public JpaOrderRepository(String puname, Map extendedPersistenceProperties){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "OrderNumericIdentifier");
    }

    @Override
    public Iterable<Ordre> findAllPreparedOrders() {
        Query query = entityManager().createQuery("SELECT ordre FROM Ordre ordre WHERE ordre.status = :status");

        query.setParameter("status", OrderStatus.PREPARED);

        return query.getResultList();
    }

    @Override
    public Iterable<Ordre> findAllDispatchedOrders() {
        Query query = entityManager().createQuery("SELECT ordre FROM Ordre ordre WHERE ordre.status = :status");

        query.setParameter("status", OrderStatus.DISPATCHED);


        return query.getResultList();
    }

    @Override
    public Optional<Ordre> ofIdentity(OrderNumericIdentifier id) {
        Query query = entityManager().createQuery("SELECT ordre FROM Ordre ordre WHERE ordre.numericIdentifier = :id");

        query.setParameter("id", id);

        return Optional.of((Ordre) query.getSingleResult());
    }

    @Override
    public List<Ordre> getOrdersByStatus(OrderStatus status) {
        Query query = entityManager().createQuery("SELECT ordre FROM Ordre ordre " +
                                                "WHERE ordre.status =: status");
        query.setParameter("status", status);
        return query.getResultList();
    }

    /*  @Override
    public List<Ordre> getClientOrders(String customer) {
        Query query = entityManager().createQuery("SELECT ordre FROM Ordre ordre " +
                "WHERE ordre.customer.systemUser.email.email =: customer");
        query.setParameter("customer", customer);
        return query.getResultList();
    }*/

    @Override
    public List<Ordre> getClientOrders(String userName) {
        Query query = entityManager().createQuery("SELECT ordre FROM Ordre ordre" +
                " WHERE ordre.customer.systemUser.username.value =: userName" +
                " AND ordre.status !=: orderStatus");
        query.setParameter("userName", userName);

        OrderStatus orderStatus = DELIVERED;
        query.setParameter("orderStatus", orderStatus);

        return query.getResultList();
    }
}
