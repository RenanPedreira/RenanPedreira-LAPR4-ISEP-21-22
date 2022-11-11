package eapli.base.warehousemanagement.domain.Warehouse;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Warehouse implements Serializable, AggregateRoot<WarehouseName> {

    @EmbeddedId
    private WarehouseName warehouseName;

    @Embedded
    private WarehousePlant plant;

    public Warehouse() {
    }

    public Warehouse(WarehouseName warehouseName) {
        this.warehouseName = warehouseName;
        this.plant = new WarehousePlant();
    }

    public Warehouse(WarehouseName warehouseName, WarehousePlant plant) {
        this.warehouseName = warehouseName;
        this.plant = plant;
    }

    public WarehousePlant getPlant() {
        return plant;
    }

    public void setPlant(final WarehousePlant plant) {
        this.plant = plant;
    }

    public WarehouseName getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(WarehouseName warehouseName) {
        this.warehouseName = warehouseName;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public WarehouseName identity() {
        return this.warehouseName;
    }

    @Override
    public String toString() {
        return "Warehouse\n\n" +
                "Warehouse name: " + warehouseName +
                "\nWarehouse plant: \n" + plant;
    }

    public String bananaSize(){
        return String.format("%d %d", this.plant.getLength(), this.plant.getWidth());
    }
}