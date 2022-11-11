package eapli.base.warehousemanagement.domain.Aisle;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccessibilityTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        Accessibility accessibility = new Accessibility(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureWhitesNotAllowed() {
        Accessibility accessibility = new Accessibility("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        Accessibility accessibility = new Accessibility("    ");
    }

    @Test
    public void ensureCreatesWithValidParameters() {
        Accessibility accessibility = new Accessibility("1234");
        assertEquals(accessibility.toString(), "1234");
    }

}