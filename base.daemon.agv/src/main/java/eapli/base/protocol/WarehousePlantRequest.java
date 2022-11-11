package eapli.base.protocol;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import eapli.base.warehousemanagement.repository.WarehouseRepository;

import java.net.UnknownHostException;
import java.util.List;

public class WarehousePlantRequest extends AGVProtocolRequest{

    public WarehousePlantRequest(String inputLine) throws UnknownHostException {
        super(inputLine);
    }

    private WarehouseRepository warehouseRepository = PersistenceContext.repositories().warehouses();

    public String execute(){
        return getWarehouseFromRequest();
    }

    public String getWarehouseFromRequest(){
        StringBuilder result = new StringBuilder();

        List<Warehouse> warehouses = warehouseRepository.getAllWarehouses();

        for(Warehouse warehouse : warehouses){
            if(warehouse.getWarehouseName().toString().compareTo("Armstrong Warehouse")==0)
                result.append(warehouse.bananaSize());
        }
        result.append("\n");

        return result.toString();
    }
}
