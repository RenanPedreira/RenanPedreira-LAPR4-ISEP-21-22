package eapli.base.persistence.impl.jpa;

import eapli.base.agvmanagement.domain.AGV;
import eapli.base.agvmanagement.domain.TaskStatus;
import eapli.base.agvmanagement.persistence.AGVRepository;
import eapli.base.common.domain.model.Identifier;
import eapli.base.ordermanagement.domain.OrderNumericIdentifier;
import eapli.base.ordermanagement.domain.Ordre;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class JpaAGVRepository extends JpaAutoTxRepository<AGV, Identifier, Identifier> implements AGVRepository {
    private static final String IDENTITY_FIELD = "Identifier";

    public JpaAGVRepository(String persistenceUnitName) {
        super(persistenceUnitName, IDENTITY_FIELD);
    }

    public JpaAGVRepository(TransactionalContext tx) {
        super(tx, IDENTITY_FIELD);
    }

    @Override
    public List<AGV> getAvailableAGVs(String status) {
        Query q = entityManager().createQuery("SELECT agv FROM AGV agv " +
                                                "WHERE autonomyStatus =: status");
        return q.getResultList();
    }

    @Override
    public List<AGV> getAGVSByStatus(TaskStatus status) {
        Query query = entityManager().createQuery("SELECT agv FROM AGV agv " +
                                                "WHERE agv.taskStatus =: status");
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public List<AGV> getAGVSByID(String id) {
        Query query = entityManager().createQuery("SELECT agv FROM AGV agv " +
                                                     "WHERE agv.id.identifier = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<AGV> getAgvByWarehouse(String warehouse) {
        Query query = entityManager().createQuery("SELECT agv FROM AGV agv " +
                                                     "WHERE agv.baseLocation.warehouse.warehouseName.warehouseName = :warehouse");
        query.setParameter("warehouse", warehouse);
        return query.getResultList();
    }

    @Override
    public List<AGV> getAllAgvFromWarehouse(String warehouse) {
        Query query = entityManager().createQuery("SELECT agv FROM AGV agv " +
                                                     "WHERE agv.baseLocation.warehouse.warehouseName.warehouseName = :warehouse");
        query.setParameter("warehouse", warehouse);
        return query.getResultList();
    }

    @Override
    public Optional<AGV> ofIdentity(Identifier id) {
        Query query = entityManager().createQuery("SELECT agv FROM AGV agv " +
                                                    "WHERE agv.id = :id");
        query.setParameter("id", id);
        return Optional.of((AGV) query.getSingleResult());
    }
}
