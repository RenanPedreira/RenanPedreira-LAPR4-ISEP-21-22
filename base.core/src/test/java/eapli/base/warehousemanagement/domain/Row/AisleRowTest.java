package eapli.base.warehousemanagement.domain.Row;

import eapli.base.warehousemanagement.domain.Aisle.Accessibility;
import eapli.base.warehousemanagement.domain.Aisle.Aisle;
import eapli.base.warehousemanagement.domain.Aisle.AisleId;
import eapli.base.warehousemanagement.domain.Aisle.Square;
import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import eapli.base.warehousemanagement.domain.Warehouse.WarehouseName;
import eapli.base.warehousemanagement.domain.Warehouse.WarehousePlant;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class AisleRowTest {

    private static final RowId rowId = new RowId(12345);
    private static final Square begin = new Square(5, 1);
    private static final Square end = new Square(2, 3);
    private static final ShelveQuantity shelveQuantity = new ShelveQuantity(3);
    private static final WarehouseName warehouseName = new WarehouseName("Warehouse 1");
    private static final WarehousePlant warehousePlant = new WarehousePlant("warehouse example 1", 12, 32, 1, "cm");
    private static final Warehouse warehouse = new Warehouse(warehouseName, warehousePlant);
    private static final Aisle aisle = new Aisle(new AisleId(122), begin, end, new Square(4,10), new Accessibility("w+"), warehouse);
    private static final AisleRow AISLE_ROW = new AisleRow(rowId, begin, end, shelveQuantity, aisle);

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveValidId() {
        new AisleRow(new RowId(-4), begin, end, shelveQuantity, aisle);
    }

    @Test
    public void ensureValidRow() {
        assertNotNull(AISLE_ROW);
    }

    @Test
    public void identity() {
        assertEquals(rowId, AISLE_ROW.identity());
    }

    @Test
    public void testToString() {
        String toString = "Row id: 12345, begin square: length: 5 width: 1, end square: length: 2 width: 3, shelves quantity: 3, aisle id: 122";
        assertEquals(toString, AISLE_ROW.toString());
    }
}