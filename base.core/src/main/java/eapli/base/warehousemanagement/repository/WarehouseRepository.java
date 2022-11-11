package eapli.base.warehousemanagement.repository;

import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import eapli.base.warehousemanagement.domain.Warehouse.WarehouseName;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface WarehouseRepository extends DomainRepository<WarehouseName, Warehouse> {
    List<Warehouse> getAllWarehouses();
    List<String> getAllWarehousesNames();
    Warehouse getWarehouseByName(WarehouseName name);
    Warehouse getWarehouseByName2(String name);
}
