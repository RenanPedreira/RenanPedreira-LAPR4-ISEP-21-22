package eapli.base.warehousemanagement.service;

import eapli.base.common.util.Pair;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import eapli.base.warehousemanagement.domain.Warehouse.WarehouseName;
import eapli.base.warehousemanagement.domain.Warehouse.WarehousePlant;
import eapli.base.warehousemanagement.repository.WarehouseRepository;

public class GetWarehousePlantService {

    private final WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouses();


    public Pair<Integer,Integer> plant(){
        WarehouseName name = new WarehouseName("Armstrong Warehouse");
        Warehouse warehouse = warehouseRepository.getWarehouseByName(name);

        WarehousePlant plant = warehouse.getPlant();
        Pair<Integer,Integer> pair = new Pair<>((int)plant.getLength(),(int)plant.getWidth());

        return pair;
    }
}
