package eapli.base.warehousemanagement.application;

import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import eapli.base.warehousemanagement.dto.WarehouseDTO;
import eapli.base.warehousemanagement.service.ImportFileService;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SetWarehousePlantController {

    ImportFileService svcImport = new ImportFileService();

    public List<String> warehouses() {
        List<String> warehousesNames = new ArrayList<>();
        for (WarehouseDTO warehouseDTO : svcImport.warehouses()) {
            warehousesNames.add(warehouseDTO.getWarehouseName());
        }
        return warehousesNames;
    }

    public WarehouseDTO findWarehouseByName(String name) {
        return svcImport.findWarehouseByName(name);
    }

    public WarehouseDTO importFile(WarehouseDTO warehouseDTO, String path) throws IOException, ParseException {
        Warehouse w = this.svcImport.importPlantFromJsonFile(warehouseDTO, path);
        WarehouseDTO wDTO = new WarehouseDTO(w);
        return wDTO;
    }

    public List showAislesInformation() {
        return this.svcImport.getAisleList();
    }

    public List showRowsInformation() {
        return this.svcImport.getRowList();
    }

    public List showAgvDocksInformation() {
        return this.svcImport.getAgvDockList();
    }
}
