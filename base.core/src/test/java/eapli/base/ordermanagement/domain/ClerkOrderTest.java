package eapli.base.ordermanagement.domain;

import eapli.base.agvmanagement.domain.Weight;
import eapli.base.common.domain.model.Address;
import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.customermanagement.domain.model.*;
import eapli.base.productmanagement.domain.Producto.Price;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClerkOrderTest {

    private final Identifier customerId = new Identifier("CU-123456789");
    private final VATIdentification idVAT = new VATIdentification("456785542");
    private final PhoneNumber phoneNumber = new PhoneNumber("+351 213489785");
    private final Gender gender = new Gender("MALE");
    private final Birthdate birthdate = new Birthdate(LocalDate.EPOCH);

    private static final String VALID_ADDRESS_TYPE1 = "Delivery Address";
    private static final String VALID_ADDRESS_TYPE2 = "Billing Address";
    private static final String VALID_STREET_NAME1 = "Rua da Tecoyo";
    private static final String VALID_STREET_NAME2 = "Rua do Picoyo";
    private static final String VALID_CITY_NAME1 = "Porto";
    private static final String VALID_CITY_NAME2 = "Vienna";

    private static final int VALID_DOOR_NUMBER1 = 412;
    private static final int VALID_DOOR_NUMBER2 = 2311;
    private final Address billingAddress = new Address(VALID_ADDRESS_TYPE1, VALID_STREET_NAME1, VALID_CITY_NAME1, VALID_DOOR_NUMBER1);
    private final Address deliveryAddress = new Address(VALID_ADDRESS_TYPE2, VALID_STREET_NAME2, VALID_CITY_NAME2, VALID_DOOR_NUMBER2);

    private final OrderNumericIdentifier orderId = new OrderNumericIdentifier(2312);
    private final PaymentMethod paymentMethod = new PaymentMethod("card");
    private final Price price = new Price(23.22);
    private final Weight weight = new Weight(10.25);

    private static final SourceChannel sourceChannel = new SourceChannel("channel4");
    private static final Comment comment = new Comment("commented");

    public SystemUser registerSystemUser(){
        SystemUserBuilder builder = UserBuilderHelper.builder();
        SystemUser systemUser = builder.withUsername("Keria")
                .withPassword("K223456")
                .withName("Ryu", "Minseok")
                .withEmail("keria@gmail.com")
                .withRoles(BaseRoles.SALES_CLERK_ROLE).build();

        return systemUser;
    }

    public Customer registerCustomer(){
        SystemUserBuilder builder = UserBuilderHelper.builder();
        SystemUser systemUser = builder.withUsername("Faker")
                .withPassword("F223456")
                .withName("Lee", "Sanghyeok")
                .withEmail("faker@gmail.com")
                .withRoles(BaseRoles.CUSTOMER_ROLE).build();

        List<Address> addressList = new ArrayList<>();
        addressList.add(billingAddress);
        addressList.add(deliveryAddress);

        Customer customer = new Customer(systemUser, customerId, idVAT, phoneNumber, gender, birthdate, addressList, new History(new IdentifierGenerator().generateIdentifier(), new ArrayList<>(), new ArrayList<>()));
        return customer;
    }

    @Test
    public void ensureCreatesValidClerkOrder(){
        SystemUser clerk = registerSystemUser();
        Customer customer = registerCustomer();

        ClerkOrder order = new ClerkOrder(clerk, sourceChannel, comment, orderId, customer, billingAddress,
                deliveryAddress, paymentMethod, price, weight);
        assertNotNull(order);
    }

}