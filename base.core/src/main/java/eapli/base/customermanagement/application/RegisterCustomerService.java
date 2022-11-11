package eapli.base.customermanagement.application;

import eapli.base.cartmanagement.domain.Cart;
import eapli.base.cartmanagement.persistence.CartRepository;
import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.Address;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.common.util.Triple;
import eapli.base.customermanagement.domain.dto.CustomerDTO;
import eapli.base.customermanagement.domain.factory.CustomerBuilder;
import eapli.base.customermanagement.domain.model.*;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.*;

import java.time.LocalDate;
import java.util.*;

/**
 * Data Structure which is responsible for managing communication between the Application Layer and the Domain Layer;
 *
 * @author Gonçalo Monteiro
 */
@ApplicationService
public class RegisterCustomerService {
    private static final int PASSWORD_LENGTH = 16;

    public CustomerDTO buildCustomer(String firstName,
                                     String lastName,
                                     String email,
                                     String phoneNumber,
                                     String idVAT,
                                     LocalDate birthdate,
                                     String gender,
                                     Map<Triple<String, String, Integer>, String> addressList)
    {
        StringBuilder builder1 = new StringBuilder();
        SystemUserBuilder builder2 = new SystemUserBuilder(new BasePasswordPolicy(), new PlainTextEncoder());
        CustomerBuilder builder3 = new CustomerBuilder();

        builder1.append(firstName).append("_").append(lastName);

        String username = builder1.toString();
        Set<Role> roleSet = new HashSet<>();

        roleSet.add(BaseRoles.CUSTOMER_ROLE);
        builder2.with(username, new RandomRawPassword(PASSWORD_LENGTH).toString(), firstName, lastName, email);
        builder2.withRoles(roleSet);
        builder3.setSystemUser(builder2.build());
        builder3.setIdentifier();
        builder3.setPhoneNumber(phoneNumber);
        builder3.setIdVAT(idVAT);

        if (birthdate != null)
            builder3.setBirthdate(birthdate);
        if (gender != null)
            builder3.setGender(gender);
        if (addressList.isEmpty())
            builder3.setAddressList(addressList);

        return builder3.build().toDTO();
    }

    public void persistsCustomer(CustomerDTO customerDTO) {
        UserManagementService userService = AuthzRegistry.userService();
        Set<Role> roleSet = new HashSet<>();

        for (String roleName : customerDTO.getRoleSet())
            roleSet.add(Role.valueOf(roleName));

        SystemUser systemUser = userService.registerNewUser(customerDTO.getUserName(),
                customerDTO.getPassword(),
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getEmail(),
                roleSet);

        CustomerRepository repository = PersistenceContext.repositories().customerRepository();
        CartRepository cartRepository = PersistenceContext.repositories().cartRepository();
        List<Address> addressList = new ArrayList<>();

        for (Map.Entry<Triple<String, String, Integer>, String> entry : customerDTO.getAddressList().entrySet())
            addressList.add(new Address(entry.getValue(),
                    entry.getKey().getFirstElement(),
                    entry.getKey().getSecondElement(),
                    entry.getKey().getThirdElement())
            );

        Customer customer = new Customer(systemUser,
                new Identifier(customerDTO.getIdentifier()),
                new VATIdentification(customerDTO.getIdVAT()),
                new PhoneNumber(customerDTO.getPhoneNumber()),
                customerDTO.getGender() != null ? new Gender(customerDTO.getGender()) : null,
                customerDTO.getBirthdate() != null ? new Birthdate(customerDTO.getBirthdate()) : null,
                addressList,
                new History(new IdentifierGenerator().generateIdentifier(), new ArrayList<>(), new ArrayList<>())
        );

        Customer customer2 = repository.save(customer);
        Cart cart = new Cart(customer2);
        cartRepository.save(cart);
    }
}
