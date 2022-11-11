package eapli.base.agvmanagement.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class PositionTest {

    private static final int validXPosition = 3;
    private static final int validYPosition = 5;

    @Test
    public void ensureCreatesWithValidPositions() {
        Position position = new Position(validXPosition, validYPosition);
        assertNotNull(position);
    }
}