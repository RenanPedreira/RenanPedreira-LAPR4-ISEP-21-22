package eapli.base.warehousemanagement.domain.Warehouse;

import eapli.framework.domain.model.DomainFactory;

public class WarehouseFactory implements DomainFactory<Warehouse> {
    private WarehouseName name;
    private WarehousePlant plant;
    private Warehouse warehouse;

    public WarehouseFactory name(final WarehouseName name)
    {
        this.name = name;
        return this;
    }

    public WarehouseFactory plant(final WarehousePlant plant)
    {
        this.plant = plant;
        return this;
    }

    private Warehouse buildOrThrow() {
        if(warehouse != null) {
            return warehouse;
        } else if(name != null & plant != null){
            warehouse = new Warehouse(name, plant);
        }
        return warehouse;
    }

    @Override
    public Warehouse build() {
        final Warehouse war = buildOrThrow();
        return war;
    }
}
