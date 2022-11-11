package eapli.base.warehousemanagement.domain.Row;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShelveQuantityTest {
    @Test(expected = IllegalArgumentException.class)
    public void ensureIsNotNegativeValue() {
        ShelveQuantity shelveQuantity = new ShelveQuantity(-1);
    }

    @Test
    public void ensureCreatesWithValidParameters() {
        ShelveQuantity shelveQuantity = new ShelveQuantity(9);
        assertEquals(Long.parseLong(shelveQuantity.toString()) , 9);
    }
}