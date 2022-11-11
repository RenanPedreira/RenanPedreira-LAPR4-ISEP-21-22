package eapli.base.warehousemanagement.dto;

import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import java.util.ArrayList;
import java.util.List;

public class WarehouseMapper {

    public List<WarehouseDTO> toDTO(List<Warehouse> warehouseList){
        List<WarehouseDTO> warehouseDTOList = new ArrayList<>();

        for(Warehouse w : warehouseList) {
            WarehouseDTO warehouseDTO = new WarehouseDTO(w);
            warehouseDTOList.add(warehouseDTO);
        }
        return warehouseDTOList;
    }
}
