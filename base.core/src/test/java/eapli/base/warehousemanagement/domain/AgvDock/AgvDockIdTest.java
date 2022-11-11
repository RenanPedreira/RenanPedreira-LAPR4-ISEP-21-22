package eapli.base.warehousemanagement.domain.AgvDock;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AgvDockIdTest {

    private static final AgvDockId agvDockId = new AgvDockId("1234");

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        AgvDockId agvDockId = new AgvDockId(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureWhitesNotAllowed() {
        AgvDockId agvDockId = new AgvDockId("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        AgvDockId agvDockId = new AgvDockId("    ");
    }

    @Test
    public void ensureCreatesWithValidParameters() {
        assertEquals(agvDockId.toString(), "1234");
    }


}