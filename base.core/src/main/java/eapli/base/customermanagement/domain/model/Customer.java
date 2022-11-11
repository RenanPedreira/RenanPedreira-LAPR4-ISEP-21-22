package eapli.base.customermanagement.domain.model;

import eapli.base.cartmanagement.domain.Cart;
import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.Address;
import eapli.base.common.util.Triple;
import eapli.base.customermanagement.domain.dto.CustomerDTO;
import eapli.base.customermanagement.domain.dto.HistoryMapper;
import eapli.base.questionnairemanagement.domain.TargetAudience;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserDetails;
import eapli.framework.representations.dto.DTOable;

import javax.persistence.*;
import java.util.*;

/**
 * Data Structure which represents the Domain Concept "Customer" (c.f. User_Management_DM.png)
 *
 * @author Gonçalo Monteiro
 */
@Entity
public class Customer implements AggregateRoot<Identifier>, DTOable<CustomerDTO>{
    @EmbeddedId
    private Identifier identifier;
    @Embedded
    private VATIdentification idVAT;
    @Embedded
    private PhoneNumber phoneNumber;
    @Embedded
    private Gender gender;
    @Embedded
    private Birthdate birthdate;
    @ElementCollection
    private List<Address> addressList;
    @OneToOne(cascade = CascadeType.ALL)
    private SystemUser systemUser;
    @OneToOne(cascade = CascadeType.ALL)
    private History history;

    public Customer(SystemUser systemUser,
                    Identifier identifier,
                    VATIdentification idVAT,
                    PhoneNumber phoneNumber,
                    Gender gender,
                    Birthdate birthdate,
                    List<Address> addressList,
                    History history)
    {
        this.systemUser = systemUser;
        this.identifier = identifier;
        this.idVAT = idVAT;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthdate = birthdate;
        this.addressList = addressList;
        this.history = history;
    }

    protected Customer() {}

    @Override
    public CustomerDTO toDTO() {
        SystemUserDetails userDetails = new SystemUserDetails(systemUser);
        Set<String> auxSet = new HashSet<>();
        Map<Triple<String, String, Integer>, String> auxMap = new HashMap<>();

        for (Role role : systemUser().roleTypes())
            auxSet.add(role.toString());

        if (addressList != null) {
            for (Address address : addressList)
                auxMap.put(new Triple<>(address.getStreetName(),
                                address.getCityName(),
                                address.getDoorNumber()),
                        address.getAddressType());
        }

        return new CustomerDTO(systemUser.username().toString(),
                systemUser.name().firstName(),
                systemUser.name().lastName(),
                systemUser.email().toString(),
                userDetails.getPassword(),
                auxSet,
                identifier != null ? identifier.toString() : null,
                birthdate != null ? birthdate.getBirthdate() : null,
                gender != null ? gender.toString() : null,
                phoneNumber.toString(),
                idVAT.toString(),
                auxMap,
                HistoryMapper.toDTO(history));
    }

    @Override
    public boolean equals(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
    return DomainEntities.areEqual(this, other);
    }

    @Override
    public Identifier identity() {
        return identifier;
    }

    public SystemUser systemUser() {
        return systemUser;
    }

    public Identifier identifier() {
        return identity();
    }

    public VATIdentification idVAT() {
        return idVAT;
    }

    public PhoneNumber phoneNumber() {
        return phoneNumber;
    }

    public Gender gender() {
        return gender;
    }

    public Birthdate birthdate() {
        return birthdate;
    }

    public List<Address> addressList() {
        return addressList;
    }

    public History history() {
        return history;
    }
}
