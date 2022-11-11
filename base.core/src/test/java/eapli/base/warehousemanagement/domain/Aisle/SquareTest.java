package eapli.base.warehousemanagement.domain.Aisle;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SquareTest {

    private final static Square square = new Square(1,2);

    @Test
    public void ensureCreatesWithValidParameters() {
        assertEquals(square.toString(),"length: 1 width: 2");
    }

    @Test
    public void ensureValidSquare() {
        assertNotNull(square);
    }
}