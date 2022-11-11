package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDock;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDockId;
import eapli.base.warehousemanagement.repository.AgvDockRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;

public class JpaAgvDockRepository extends JpaAutoTxRepository<AgvDock, AgvDockId, AgvDockId> implements AgvDockRepository {

     public JpaAgvDockRepository(TransactionalContext autoTx){
        super(autoTx, "AgvDockId");
    }

    public JpaAgvDockRepository(String puname, Map extendedPersistenceProperties){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "AgvDockId");
    }
}
