package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.Row.AisleRow;
import eapli.base.warehousemanagement.domain.Row.RowId;

import eapli.base.warehousemanagement.repository.RowRepository;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;

public class inMemoryRowRepository extends InMemoryDomainRepository<AisleRow, RowId> implements RowRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<AisleRow> getAllRow() {
        return null;
    }
}
