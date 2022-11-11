package eapli.base.customermanagement.domain.dto;

import eapli.base.common.domain.model.Address;
import eapli.base.common.domain.model.Identifier;
import eapli.base.common.util.Triple;
import eapli.base.customermanagement.domain.model.*;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.model.SystemUserDetails;

import java.util.*;

public class CustomerMapper {
    public static CustomerDTO toDTO(Customer customer) {
        SystemUserDetails userDetails = new SystemUserDetails(customer.systemUser());
        Set<String> auxSet = new HashSet<>();
        Map<Triple<String, String, Integer>, String> auxMap = new HashMap<>();

        for (Role role : customer.systemUser().roleTypes())
            auxSet.add(role.toString());

        if (customer.addressList() != null) {
            for (Address address : customer.addressList())
                auxMap.put(new Triple<>(address.getStreetName(),
                                address.getCityName(),
                                address.getDoorNumber()),
                        address.getAddressType());
        }

        return new CustomerDTO(customer.systemUser().username().toString(),
                customer.systemUser().name().firstName(),
                customer.systemUser().name().lastName(),
                customer.systemUser().email().toString(),
                userDetails.getPassword(),
                auxSet,
                customer.identifier() != null ? customer.identifier().toString() : null,
                customer.birthdate() != null ? customer.birthdate().getBirthdate() : null,
                customer.gender() != null ? customer.gender().toString() : null,
                customer.phoneNumber().toString(),
                customer.idVAT().toString(),
                auxMap,
                HistoryMapper.toDTO(customer.history()));
    }

    public static Customer toCustomer(CustomerDTO customerDTO) {
        SystemUserBuilder userBuilder = new SystemUserBuilder(new BasePasswordPolicy(), new PlainTextEncoder());
        Set<Role> roleSet = new HashSet<>();
        List<Address> addressList = new ArrayList<>();

        roleSet.add(BaseRoles.CUSTOMER_ROLE);
        userBuilder.with(customerDTO.getUserName(),
                customerDTO.getPassword(),
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getEmail());
        userBuilder.withRoles(roleSet);

        for (Map.Entry<Triple<String, String, Integer>, String> entry : customerDTO.getAddressList().entrySet())
            addressList.add(new Address(entry.getValue(),
                    entry.getKey().getFirstElement(),
                    entry.getKey().getSecondElement(),
                    entry.getKey().getThirdElement()));

        return new Customer(userBuilder.build(),
                new Identifier(customerDTO.getIdentifier()),
                new VATIdentification(customerDTO.getIdVAT()),
                new PhoneNumber(customerDTO.getPhoneNumber()),
                new Gender(customerDTO.getGender()),
                new Birthdate(customerDTO.getBirthdate()),
                addressList,
                HistoryMapper.toHistory(customerDTO.getHistory()));
    }
}
