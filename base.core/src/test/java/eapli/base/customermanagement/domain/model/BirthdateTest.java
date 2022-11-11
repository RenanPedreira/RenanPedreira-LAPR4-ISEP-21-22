package eapli.base.customermanagement.domain.model;

import eapli.base.customermanagement.domain.model.Birthdate;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BirthdateTest {
    private static final int DEFAULT_YEAR1 = 2020;
    private static final int DEFAULT_YEAR2 = 1996;
    private static final int DEFAULT_YEAR3 = 1918;
    private static final int DEFAULT_MONTH1 = 12;
    private static final int DEFAULT_MONTH2 = 8;
    private static final int DEFAULT_DAY1 = 4;
    private static final int DEFAULT_DAY2 = 16;
    private static final LocalDate VALID_BIRTHDATE1 = LocalDate.of(DEFAULT_YEAR1, DEFAULT_MONTH1, DEFAULT_DAY1);
    private static final LocalDate VALID_BIRTHDATE2 = LocalDate.of(DEFAULT_YEAR2, DEFAULT_MONTH1, DEFAULT_DAY1);
    private static final LocalDate VALID_BIRTHDATE3 = LocalDate.of(DEFAULT_YEAR1, DEFAULT_MONTH2, DEFAULT_DAY1);
    private static final LocalDate VALID_BIRTHDATE4 = LocalDate.of(DEFAULT_YEAR1, DEFAULT_MONTH1, DEFAULT_DAY2);
    private static final LocalDate INVALID_BIRTHDATE1 = null;
    private static final LocalDate INVALID_BIRTHDATE2 = LocalDate.of(DEFAULT_YEAR3, DEFAULT_MONTH1, DEFAULT_DAY1);

    private Birthdate birthdate;

    @Before
    public void setUp() {
        birthdate = new Birthdate(VALID_BIRTHDATE1);
    }

    @Test
    public void buildTest() {
        assertThrows(IllegalArgumentException.class, () -> new Birthdate(INVALID_BIRTHDATE1));
        assertThrows(IllegalArgumentException.class, () -> new Birthdate(INVALID_BIRTHDATE2));
        assertDoesNotThrow(() -> new Birthdate(VALID_BIRTHDATE1));
    }

    @Test
    public void compareToTest() {
        assertTrue(birthdate.compareTo(birthdate) == 0);
        assertTrue(birthdate.compareTo(new Birthdate(VALID_BIRTHDATE2)) >= 1);
        assertTrue(birthdate.compareTo(new Birthdate(VALID_BIRTHDATE3)) >= 1);
        assertTrue(birthdate.compareTo(new Birthdate(VALID_BIRTHDATE4)) <= -1);
    }

    @Test
    public void toStringTest() {
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();

        builder1.append("0").append(DEFAULT_DAY1);

        builder2.append(DEFAULT_YEAR1).append("-")
                .append(DEFAULT_MONTH1).append("-")
                .append(builder1);

        String expRes = builder2.toString();

        assertEquals(expRes, birthdate.toString());
    }

    @Test
    public void getBirthdateTest() {
        LocalDate expRes = VALID_BIRTHDATE1;

        assertNotNull(birthdate.getBirthdate());
        assertEquals(expRes, birthdate.getBirthdate());

        LocalDate notExpRes = VALID_BIRTHDATE2;

        assertNotEquals(notExpRes, birthdate.getBirthdate());
    }
}