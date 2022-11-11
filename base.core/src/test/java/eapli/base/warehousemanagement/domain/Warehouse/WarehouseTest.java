package eapli.base.warehousemanagement.domain.Warehouse;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class WarehouseTest {

    private static final WarehouseName warehouseName = new WarehouseName("AMAZON KOREA WAREHOUSE");
    private static final WarehousePlant warehousePlant = new WarehousePlant("Very big", 1000, 2333, 11111, "cm");
    private static final Warehouse warehouse = new Warehouse(warehouseName, warehousePlant);

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveValidId() {
        new Warehouse(new WarehouseName(null), warehousePlant);
    }

    @Test
    public void ensureValidWarehouse() {
        assertNotNull(warehouse);
    }

    @Test
    public void identity() {
        assertEquals(warehouseName, warehouse.identity());
    }

    @Test
    public void testToString() {
        String toString = "Warehouse\n\n" +
                "Warehouse name: " + warehouseName +
                "\nWarehouse plant: \n" + warehousePlant;
        assertEquals(toString, warehouse.toString());
    }
}