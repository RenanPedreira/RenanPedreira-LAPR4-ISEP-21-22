package eapli.base.warehousemanagement.domain.Warehouse;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WarehousePlantTest {

    @Test
    public void ensureCreatesWithValidParameters() {
        WarehousePlant warehousePlant = new WarehousePlant("1",1,2,3,"1");
        assertEquals(warehousePlant.toString(), "description: 1, length: 1, width: 2, square: 3, unit: 1");
    }
}