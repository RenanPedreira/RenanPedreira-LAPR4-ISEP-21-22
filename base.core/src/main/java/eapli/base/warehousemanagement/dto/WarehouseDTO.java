package eapli.base.warehousemanagement.dto;

import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;

public class WarehouseDTO {

    private String warehouseName;
    private String warehousePlant;

    public WarehouseDTO(Warehouse w) {
        this.warehouseName = w.getWarehouseName().toString();
        this.warehousePlant = w.getPlant().toString();
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public String showInformation(){
        return "Warehouse name: " + warehouseName +
                "\nWarehouse plant: " + warehousePlant;
    }
}
