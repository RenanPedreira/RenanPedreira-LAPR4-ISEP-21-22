package eapli.base.persistence.impl.jpa;

import eapli.base.Application;


import eapli.base.warehousemanagement.domain.Row.AisleRow;
import eapli.base.warehousemanagement.domain.Row.RowId;
import eapli.base.warehousemanagement.repository.RowRepository;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;


import javax.persistence.Query;
import java.util.List;
import java.util.Map;


public class JpaRowRepository extends JpaAutoTxRepository<AisleRow, RowId, RowId>
        implements RowRepository {

    public JpaRowRepository(final TransactionalContext autoTx) {
        super(autoTx, "RowId");
    }

    public JpaRowRepository(final String puname, Map extendedPersistenceProperties){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "RowId");
    }


    @Override
    public List<AisleRow> getAllRow() {
        Query q = entityManager().createQuery("SELECT wh FROM AisleRow wh");
        return q.getResultList();
    }

}

