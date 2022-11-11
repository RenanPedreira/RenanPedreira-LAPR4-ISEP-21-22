package eapli.base.warehousemanagement.domain.Warehouse;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WarehouseNameTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        WarehouseName warehouseName = new WarehouseName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureWhitesNotAllowed() {
        WarehouseName warehouseName = new WarehouseName("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        WarehouseName warehouseName = new WarehouseName("    ");
    }

    @Test
    public void ensureCreatesWithValidParameters() {
        WarehouseName warehouseName = new WarehouseName("Lisbon");
        assertEquals(warehouseName.toString(), "Lisbon");
    }
}