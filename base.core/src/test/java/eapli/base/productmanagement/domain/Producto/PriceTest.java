package eapli.base.productmanagement.domain.Producto;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriceTest {

    private static final double validPrice = 100;
    private static final double invalidPrice = -50;

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveValidPrice() {
        Price price = new Price(invalidPrice);
    }

    @Test
    public void ensureCreatesValidPrice() {
        Price price = new Price(validPrice);
        assertNotNull(price);
    }
}