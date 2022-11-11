package eapli.base.warehousemanagement.repository;

import eapli.base.warehousemanagement.domain.Row.AisleRow;
import eapli.base.warehousemanagement.domain.Row.RowId;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface RowRepository extends DomainRepository<RowId, AisleRow> {
    List<AisleRow> getAllRow();
}
