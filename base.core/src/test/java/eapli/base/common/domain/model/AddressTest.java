package eapli.base.common.domain.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {
    private static final String VALID_ADDRESS_TYPE1 = "Delivery Address";
    private static final String VALID_ADDRESS_TYPE2 = "Billing Address";
    private static final String INVALID_ADDRESS_TYPE1 = "Tecoyo Address";
    private static final String INVALID_ADDRESS_TYPE2 = null;
    private static final String VALID_STREET_NAME1 = "Rua da Tecoyo";
    private static final String VALID_STREET_NAME2 = "Rua do Picoyo";
    private static final String INVALID_STREET_NAME1 = "<3 Rua da Tecoyo";
    private static final String INVALID_STREET_NAME2 = "";
    private static final String INVALID_STREET_NAME3 = null;
    private static final String VALID_CITY_NAME1 = "Porto";
    private static final String VALID_CITY_NAME2 = "Vienna";
    private static final String INVALID_CITY_NAME1 = "Tokyo <3";
    private static final String INVALID_CITY_NAME2 = "";
    private static final String INVALID_CITY_NAME3 = null;

    private static final int VALID_DOOR_NUMBER1 = 412;
    private static final int VALID_DOOR_NUMBER2 = 2311;
    private static final int INVALID_DOOR_NUMBER1 = -4;

    private Address address;

    @Before
    public void setUp() {
        address = new Address(VALID_ADDRESS_TYPE1, VALID_STREET_NAME1, VALID_CITY_NAME1, VALID_DOOR_NUMBER1);
    }

    @Test
    public void buildTest() {
        assertThrows(IllegalArgumentException.class, () -> new Address(INVALID_ADDRESS_TYPE1, VALID_STREET_NAME1, VALID_CITY_NAME1, VALID_DOOR_NUMBER1));
        assertThrows(IllegalArgumentException.class, () -> new Address(INVALID_ADDRESS_TYPE2, VALID_STREET_NAME1, VALID_CITY_NAME1, VALID_DOOR_NUMBER1));
        assertThrows(IllegalArgumentException.class, () -> new Address(VALID_ADDRESS_TYPE1, INVALID_STREET_NAME1, VALID_CITY_NAME1, VALID_DOOR_NUMBER1));
        assertThrows(IllegalArgumentException.class, () -> new Address(VALID_ADDRESS_TYPE1, INVALID_STREET_NAME2, VALID_CITY_NAME1, VALID_DOOR_NUMBER1));
        assertThrows(IllegalArgumentException.class, () -> new Address(VALID_ADDRESS_TYPE1, INVALID_STREET_NAME3, VALID_CITY_NAME1, VALID_DOOR_NUMBER1));
        assertThrows(IllegalArgumentException.class, () -> new Address(VALID_ADDRESS_TYPE1, VALID_STREET_NAME1, INVALID_CITY_NAME1, VALID_DOOR_NUMBER1));
        assertThrows(IllegalArgumentException.class, () -> new Address(VALID_ADDRESS_TYPE1, VALID_STREET_NAME1, INVALID_CITY_NAME2, VALID_DOOR_NUMBER1));
        assertThrows(IllegalArgumentException.class, () -> new Address(VALID_ADDRESS_TYPE1, VALID_STREET_NAME1, INVALID_CITY_NAME3, VALID_DOOR_NUMBER1));
        assertThrows(IllegalArgumentException.class, () -> new Address(VALID_ADDRESS_TYPE1, VALID_STREET_NAME1, VALID_CITY_NAME1, INVALID_DOOR_NUMBER1));
        assertDoesNotThrow(() -> new Address(VALID_ADDRESS_TYPE1, VALID_STREET_NAME1, VALID_CITY_NAME1, VALID_DOOR_NUMBER1));
        assertDoesNotThrow(() -> new Address(VALID_ADDRESS_TYPE2, VALID_STREET_NAME1, VALID_CITY_NAME1, VALID_DOOR_NUMBER1));
    }

    @Test
    public void compareToTest() {
        assertTrue(address.compareTo(address) == 0);
        assertTrue(address.compareTo(new Address(VALID_ADDRESS_TYPE2, VALID_STREET_NAME1, VALID_CITY_NAME1, VALID_DOOR_NUMBER1)) >= 1);
        assertTrue(address.compareTo(new Address(VALID_ADDRESS_TYPE1, VALID_STREET_NAME2, VALID_CITY_NAME1, VALID_DOOR_NUMBER1)) <= -1);
        assertTrue(address.compareTo(new Address(VALID_ADDRESS_TYPE1, VALID_STREET_NAME1, VALID_CITY_NAME2, VALID_DOOR_NUMBER1)) <= -1);
        assertTrue(address.compareTo(new Address(VALID_ADDRESS_TYPE1, VALID_STREET_NAME1, VALID_CITY_NAME1, VALID_DOOR_NUMBER2)) <= -1);
    }

    @Test
    public void toStringTest() {
        StringBuilder builder = new StringBuilder();
        String auxVar = VALID_ADDRESS_TYPE1;

        auxVar = auxVar.toUpperCase();
        auxVar = auxVar.replace(" ", "_");
        builder.append(auxVar).append("\n")
                .append(VALID_STREET_NAME1).append(", ")
                .append(VALID_DOOR_NUMBER1).append("\n")
                .append(VALID_CITY_NAME1);

        String expRes = builder.toString();

        assertNotNull(address.toString());
        assertEquals(expRes, address.toString());
    }

    @Test
    public void getAddressTypeTest() {
        String expRes = VALID_ADDRESS_TYPE1;

        expRes = expRes.toUpperCase();
        expRes = expRes.replace(" ", "_");

        assertNotNull(address.getAddressType());
        assertEquals(expRes, address.getAddressType());

        String notExpRes = VALID_ADDRESS_TYPE2;
        notExpRes = notExpRes.toUpperCase();
        notExpRes = notExpRes.replace(" ", "_");

        assertNotEquals(notExpRes, address.getAddressType());
    }

    @Test
    public void getStreetNameTest() {
        String expRes = VALID_STREET_NAME1;

        assertNotNull(address.getStreetName());
        assertEquals(expRes, address.getStreetName());

        String notExpRes = VALID_STREET_NAME2;

        assertNotEquals(notExpRes, address.getAddressType());
    }

    @Test
    public void getCityNameTest() {
        String expRes = VALID_CITY_NAME1;

        assertNotNull(address.getCityName());
        assertEquals(expRes, address.getCityName());

        String notExpRes = VALID_CITY_NAME2;

        assertNotEquals(notExpRes, address.getCityName());
    }

    @Test
    public void getDoorNumberTest() {
        int expRes = VALID_DOOR_NUMBER1;

        assertNotNull(address.getDoorNumber());
        assertEquals(expRes, address.getDoorNumber());

        int notExpRes = VALID_DOOR_NUMBER2;

        assertNotEquals(notExpRes, address.getDoorNumber());
    }
}