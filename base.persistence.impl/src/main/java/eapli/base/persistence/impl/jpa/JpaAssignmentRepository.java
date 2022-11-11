package eapli.base.persistence.impl.jpa;


import eapli.base.Application;
import eapli.base.common.domain.model.Identifier;
import eapli.base.warehousemanagement.domain.Assignment.Assignment;
import eapli.base.warehousemanagement.repository.AssignmentRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;


public class JpaAssignmentRepository  extends JpaAutoTxRepository<Assignment, Identifier, Identifier>
        implements AssignmentRepository {

    private static final String IDENTITY_FIELD = "Identifier";


    public JpaAssignmentRepository(String puname, Map extendedPersistenceProperties){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "InternalCode");
    }
    public JpaAssignmentRepository(TransactionalContext tx) {
        super(tx, IDENTITY_FIELD);
    }


    @Override
    public List<Assignment> getAgvAssignments(String agv) {
        Query q = entityManager().createQuery("SELECT assig FROM Assignment assig " +
                                                 "WHERE assig.agv.identifier.identifier = :agv");
        q.setParameter("agv", agv);
        return q.getResultList();
    }
}
