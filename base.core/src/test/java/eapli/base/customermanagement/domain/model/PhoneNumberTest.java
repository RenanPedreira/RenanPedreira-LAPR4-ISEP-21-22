package eapli.base.customermanagement.domain.model;

import eapli.base.customermanagement.domain.model.PhoneNumber;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberTest {
    private static final String VALID_NUMBER1 = "+351 123456789";
    private static final String VALID_NUMBER2 = "+49 351 125-3456";
    private static final String INVALID_NUMBER1 = "<304122311";
    private static final String INVALID_NUMBER2 = "041  223113";

    private PhoneNumber phoneNumber;

    @Before
    public void setUp() {
        try {
            phoneNumber = new PhoneNumber(VALID_NUMBER1);
        } catch (IllegalArgumentException ignored) {}
    }

    /*
    @Test
    public void buildTest() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(INVALID_NUMBER1));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(INVALID_NUMBER2));
        assertDoesNotThrow(() -> new PhoneNumber(VALID_NUMBER1));
        assertDoesNotThrow(() -> new PhoneNumber(VALID_NUMBER2));
    }
     */

    @Test
    public void getPhoneNumberTest() {
        String expRes = VALID_NUMBER1;

        assertNotNull(phoneNumber.getPhoneNumber());
        assertEquals(expRes, phoneNumber.getPhoneNumber());

        expRes = VALID_NUMBER2;

        assertNotEquals(expRes, phoneNumber.getPhoneNumber());
    }

    @Test
    public void compareToTest() {
        assertTrue(phoneNumber.compareTo(phoneNumber) == 0);
        try {
            assertTrue(new PhoneNumber(VALID_NUMBER2).compareTo(phoneNumber) >= 1);
            assertTrue(phoneNumber.compareTo(new PhoneNumber(VALID_NUMBER2)) <= -1);
        } catch (IllegalArgumentException ignored) {}
    }

    @Test
    public void toStringTest() {
        String expRes = VALID_NUMBER1;

        assertNotNull(phoneNumber.toString());
        assertEquals(expRes, phoneNumber.toString());

        expRes = VALID_NUMBER2;

        assertNotEquals(expRes, phoneNumber.toString());
    }
}