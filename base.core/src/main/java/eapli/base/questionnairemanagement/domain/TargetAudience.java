package eapli.base.questionnairemanagement.domain;

import eapli.base.common.domain.model.Identifier;
import eapli.base.customermanagement.domain.ClientUser;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class TargetAudience implements DomainEntity<Identifier> {
    @EmbeddedId
    private Identifier identifier;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "TargetCustomer")
    private List<Customer> targetCustomers;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "RespondingCustomer")
    private List<Customer> respondingCustomers;

    public TargetAudience(Identifier identifier, List<Customer> targetCustomers, List<Customer> respondingCustomers) {
        this.identifier = identifier;
        this.targetCustomers = targetCustomers;
        this.respondingCustomers = respondingCustomers;
    }

    protected TargetAudience() {}

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Identifier identity() {
        return identifier;
    }

    public Identifier identifier() {
        return identity();
    }

    public List<Customer> targetCustomers() {
        return targetCustomers;
    }

    public List<Customer> respondingCustomers() {
        return respondingCustomers;
    }

    public void addCustomerToTargetAudience(Customer customer) {
        this.targetCustomers.add(customer);
    }

    public void newCustomerResponse(Customer customer) {
        this.respondingCustomers.add(customer);
    }
}
