package eapli.base.agvmanagement.domain;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeightTest {

    private static final double validWeight = 100;
    private static final double invalidWeight1 = -10;
    private static final double invalidWeight2 = 10000;

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveNonNegativeWeight() {
        new Weight(invalidWeight1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveValidWeight() {
        new Weight(invalidWeight2);
    }

    @Test
    public void ensureCreatesWithValidWeight() {
        Weight weight = new Weight(validWeight);
        assertNotNull(weight);
    }

}