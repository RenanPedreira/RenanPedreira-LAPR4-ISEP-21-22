package eapli.base.customermanagement.domain.model;

import eapli.base.common.domain.model.Address;
import eapli.base.customermanagement.domain.model.*;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.*;
import org.h2.engine.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    /**
    private static final Username DEFAULT_USERNAME1 = Username.valueOf("SuperTecoyo");
    private static final Name DEFAULT_NAME = Name.valueOf("Super", "Tecoyo");
    private static final EmailAddress DEFAULT_EMAIL = EmailAddress.valueOf("tecoyo23@gmail.com");
    private static final Password DEFAULT_PASSWORD = Password.encodedAndValid("123BasicPassword", new BasePasswordPolicy(), new PlainTextEncoder()).get();
    private static final Role DEFAULT_ROLE = Role.valueOf("CUSTOMER_ROLE");
    private static final Identifier DEFAULT_IDENTIFIER_1 = new Identifier("CU-123456789");
    private static final Identifier DEFAULT_IDENTIFIER_2 = new Identifier("CU-231141216");
    private static final VATIdentification DEFAULT_VAT = new VATIdentification("PT123456789");
    private static final PhoneNumber DEFAULT_PHONE_NUMBER = new PhoneNumber("+351 123456789");
    private static final Gender DEFAULT_GENDER = new Gender("Non-Binary");
    private static final Birthdate DEFAULT_BIRTHDATE = new Birthdate(LocalDate.of(2020, 12, 4));
    private static final Address DEFAULT_ADDRESS1 = new Address("Delivery Address", "Rua da Tecoyo", "Porto", 412);
    private static final Address DEFAULT_ADDRESS2 = new Address("Billing Address", "Rua Pico Monteiro", "Vienna", 2311);

    private UserManagementService userService;

    private Customer customer;

    @Before
    public void setUp() {
        userService = AuthzRegistry.userService();

        Set<Role> roleSet = new HashSet<>();
        List<Address> addressList = new ArrayList<>();

        roleSet.add(DEFAULT_ROLE);
        addressList.add(DEFAULT_ADDRESS1);
        addressList.add(DEFAULT_ADDRESS2);

        customer = new Customer(userService.registerUser(DEFAULT_USERNAME1, DEFAULT_PASSWORD, DEFAULT_NAME, DEFAULT_EMAIL, roleSet),
                DEFAULT_IDENTIFIER_1,
                DEFAULT_VAT,
                DEFAULT_PHONE_NUMBER,
                DEFAULT_GENDER,
                DEFAULT_BIRTHDATE,
                addressList);
    }

    @After
    public void tearDown() {
        SystemUser systemUser = userService.userOfIdentity(DEFAULT_USERNAME1).get();

        userService.deactivateUser(systemUser);
    }

    @Test
    public void testEqualsTest() {
        assertTrue(customer.equals(customer));
        assertFalse(customer.equals(new Customer(userService.userOfIdentity(DEFAULT_USERNAME1).get(),
                DEFAULT_IDENTIFIER_2, DEFAULT_VAT, DEFAULT_PHONE_NUMBER, null, null, null)));
    }

    @Test
    public void sameAsTest() {
        assertTrue(customer.sameAs(customer));
        assertFalse(customer.sameAs(new Customer(userService.userOfIdentity(DEFAULT_USERNAME1).get(),
                DEFAULT_IDENTIFIER_2, DEFAULT_VAT, DEFAULT_PHONE_NUMBER, null, null, null)));
    }

    @Test
    public void identityTest() {
        Identifier expRes = DEFAULT_IDENTIFIER_1;

        assertNotNull(customer.identity());
        assertEquals(expRes, customer.identity());
    }

    @Test
    public void identifierTest() {
        Identifier expRes = DEFAULT_IDENTIFIER_1;

        assertNotNull(customer.identifier());
        assertEquals(expRes, customer.identifier());
    }

    @Test
    public void genderTest() {
        Gender expRes = DEFAULT_GENDER;

        assertNotNull(customer.gender());
        assertEquals(expRes, customer.gender());
    }

    @Test
    public void idVATTest() {
        VATIdentification expRes = DEFAULT_VAT;

        assertNotNull(customer.idVAT());
        assertEquals(expRes, customer.idVAT());
    }

    @Test
    public void phoneNumberTest() {
        PhoneNumber expRes = DEFAULT_PHONE_NUMBER;

        assertNotNull(customer.phoneNumber());
        assertEquals(expRes, customer.phoneNumber());
    }

    @Test
    public void systemUserTest() {
        SystemUser expRes = userService.userOfIdentity(DEFAULT_USERNAME1).get();

        assertNotNull(customer.systemUser());
        assertEquals(expRes, customer.systemUser());
    }

    @Test
    public void birthdateTest() {
        Birthdate expRes = DEFAULT_BIRTHDATE;

        assertNotNull(customer.birthdate());
        assertEquals(expRes, customer.birthdate());
    }

    @Test
    public void addressListTest() {
        List<Address> expRes = new ArrayList<>();

        expRes.add(DEFAULT_ADDRESS1);
        expRes.add(DEFAULT_ADDRESS2);

        assertNotNull(customer.addressList());
        assertEquals(expRes, customer.addressList());
    }
    **/
}