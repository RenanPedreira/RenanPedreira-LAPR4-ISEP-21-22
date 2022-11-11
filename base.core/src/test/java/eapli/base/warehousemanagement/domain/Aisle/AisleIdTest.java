package eapli.base.warehousemanagement.domain.Aisle;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AisleIdTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureIsNotNegativeValue() {
        AisleId aisleId = new AisleId(-1);
    }

    @Test
    public void ensureCreatesWithValidParameters() {
        AisleId aisleId = new AisleId(9);
        assertEquals(Long.parseLong(aisleId.toString()) , 9);
    }
}