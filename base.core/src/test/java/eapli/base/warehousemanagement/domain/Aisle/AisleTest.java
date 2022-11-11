package eapli.base.warehousemanagement.domain.Aisle;

import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import eapli.base.warehousemanagement.domain.Warehouse.WarehouseName;
import eapli.base.warehousemanagement.domain.Warehouse.WarehousePlant;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class AisleTest {

    private static final AisleId aisleId = new AisleId(123);
    private static final Square begin = new Square(5, 1);
    private static final Square end = new Square(2, 3);
    private static final Square depth = new Square(3, 8);
    private static final Accessibility accessibility = new Accessibility("w+");
    private static final WarehouseName warehouseName = new WarehouseName("Warehouse 1");
    private static final WarehousePlant warehousePlant = new WarehousePlant("warehouse example 1", 12, 32, 1, "cm");
    private static final Warehouse warehouse = new Warehouse(warehouseName, warehousePlant);
    private static final Aisle aisle = new Aisle(aisleId, begin, end, depth, accessibility, warehouse);

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveValidId() {
        new Aisle(new AisleId(-2), begin, end, depth, accessibility, warehouse);
    }

    @Test
    public void ensureValidAisle() {
        assertNotNull(aisle);
    }

    @Test
    public void identity() {
        assertEquals(aisleId, aisle.identity());
    }

    @Test
    public void testToString() {
        String toString = "Aisle id: 123, begin square: length: 5 width: 1, end square: length: 2 width: 3, depth square: length: 3 width: 8, accessibility: w+";
        assertEquals(aisle.toString(), toString);
    }
}