package eapli.base.ordermanagement.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class QuantityTest {

    private static final int validQuantity = 10;
    private static final int invalidQuantity = -5;

    @Test(expected = IllegalArgumentException.class)
    public void ensureIsNotInvalidValue() {
        Quantity quantity = new Quantity(invalidQuantity);
    }

    @Test
    public void ensureValidQuantity() {
        Quantity quantity = new Quantity(validQuantity);
        assertNotNull(quantity);
    }
}