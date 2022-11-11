package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.warehousemanagement.domain.Aisle.Aisle;
import eapli.base.warehousemanagement.domain.Aisle.AisleId;
import eapli.base.warehousemanagement.repository.AisleRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.Map;

public class JpaAisleRepository extends JpaAutoTxRepository<Aisle, AisleId, AisleId> implements AisleRepository {

    public JpaAisleRepository(TransactionalContext autoTx){
        super(autoTx, "AisleId");
    }

    public JpaAisleRepository(String puname, Map extendedPersistenceProperties){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "AisleId");
    }
}
