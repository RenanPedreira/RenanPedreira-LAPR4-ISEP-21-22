package eapli.base.agvmanagement.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

public class SpeedTest {

    private static final int validSpeed = 1;
    private static final int invalidSpeed = 4;
    private static final int negativeSpeed = -3;

    @Test
    public void ensureCreatesWithValidSpeed() {
        Speed speed = new Speed(validSpeed);
        assertNotNull(speed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveValidSpeed() {
        new Speed(invalidSpeed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveNoneNegativeSpeed() {
        new Speed(negativeSpeed);
    }
}