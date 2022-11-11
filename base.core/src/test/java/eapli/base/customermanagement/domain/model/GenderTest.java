package eapli.base.customermanagement.domain.model;

import eapli.base.customermanagement.domain.model.Gender;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GenderTest {
    private static final String VALID_GENDER1 = "Non-Binary";
    private static final String VALID_GENDER2 = "Woman";
    private static final String VALID_GENDER3 = "Man";
    private static final String INVALID_GENDER = "Tecoyo";

    private Gender gender;

    @Before
    public void setUpTest() {
        try {
            gender = new Gender(VALID_GENDER1);
        } catch (IllegalArgumentException ignored) {}
    }

    /*
    @Test
    public void buildTest() {
        assertDoesNotThrow(() -> new Gender(VALID_GENDER1));
        assertDoesNotThrow(() -> new Gender(VALID_GENDER2));
        assertDoesNotThrow(() -> new Gender(VALID_GENDER3));
        assertThrows(IllegalArgumentException.class, () -> new Gender(INVALID_GENDER));
    }*/

    @Test
    public void getGenderTest() {
        String expResult = VALID_GENDER1;

        assertNotNull(gender.getGender());
        assertEquals(expResult, gender.getGender());

        expResult = VALID_GENDER2;

        assertNotEquals(expResult, gender.getGender());
    }

    @Test
    public void compareToTest() {
        assertTrue(gender.compareTo(gender) == 0);

        try {
            assertTrue(gender.compareTo(new Gender(VALID_GENDER3)) >= 1);
            assertTrue(gender.compareTo(new Gender(VALID_GENDER2)) <= -1);
        } catch (IllegalArgumentException ignored) {}
    }

    @Test
    public void toStringTest() {
        String expResult = VALID_GENDER1;

        assertNotNull(gender.toString());
        assertEquals(expResult, gender.toString());

        expResult = VALID_GENDER2;

        assertNotEquals(expResult, gender.toString());
    }
}