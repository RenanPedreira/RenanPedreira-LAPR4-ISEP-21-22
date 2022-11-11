package eapli.base.warehousemanagement.domain.Row;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RowIdTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureIsNotNegativeValue() {
        RowId rowId = new RowId(-1);
    }

    @Test
    public void ensureCreatesWithValidParameters() {
        RowId rowId = new RowId(1);
        assertEquals(Long.parseLong(rowId.toString()), 1);
    }
}