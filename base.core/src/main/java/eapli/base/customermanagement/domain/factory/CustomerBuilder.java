package eapli.base.customermanagement.domain.factory;

import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.Address;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.common.util.Triple;
import eapli.base.customermanagement.domain.model.*;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data Structure responsible for the instantiation of "Customer" Entities in a valid state;
 *
 * @author Gonçalo Monteiro
 */

public class CustomerBuilder implements DomainFactory<Customer> {
    private static final String ID_PREFIX = "CU";

    private Identifier identifier;
    private SystemUser systemUser;
    private Birthdate birthdate;
    private Gender gender;
    private PhoneNumber phoneNumber;
    private VATIdentification idVAT;
    private List<Address> addressList;
    private History history;

    public void setIdentifier(String identifier) throws IllegalArgumentException {
        if (!identifier.contains(ID_PREFIX))
            throw new IllegalArgumentException("The Identifier \"" + identifier + "\" has an invalid format.");

        this.identifier = new Identifier(identifier);
    }

    public void setIdentifier() {
        this.identifier = new IdentifierGenerator().generateIdentifier();
    }

    public void setSystemUser(SystemUser systemUser) throws IllegalArgumentException {
        if (!systemUser.roleTypes().contains(BaseRoles.CUSTOMER_ROLE))
            throw new IllegalArgumentException("The System User\"" + systemUser.username() + "\" doesn't possess the appropriate role.");

        this.systemUser = systemUser;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = new Birthdate(birthdate);
    }

    public void setGender(String gender) {
        this.gender = new Gender(gender);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }

    public void setIdVAT(String idVAT) {
        this.idVAT = new VATIdentification(idVAT);
    }

    public void setAddressList(Map<Triple<String, String, Integer>, String> addressList) {
        List<Address> auxList = new ArrayList<>();

        for (Map.Entry<Triple<String, String, Integer>, String> entry : addressList.entrySet())
            auxList.add(new Address(entry.getValue(), entry.getKey().getFirstElement(), entry.getKey().getSecondElement(), entry.getKey().getThirdElement()));

        this.addressList = auxList;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    @Override
    public Customer build() {
        return new Customer(systemUser,
                identifier,
                idVAT,
                phoneNumber,
                gender,
                birthdate,
                addressList,
                history);
    }
}
