package eapli.base.questionnairemanagement.factory;

import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.questionnairemanagement.domain.TargetAudience;

import java.util.List;

public class TargetAudienceBuilder {
    private Identifier identifier;
    private List<Customer> targetCustomers;
    private List<Customer> respondingCustomers;

    public void setIdentifier(String identifier) {
        this.identifier = new Identifier(identifier);
    }

    public void setIdentifier() {
        this.identifier = new IdentifierGenerator().generateIdentifier();
    }

    public void setTargetCustomers(List<Customer> targetCustomers) {
        this.targetCustomers = targetCustomers;
    }

    public void setRespondingCustomers(List<Customer> respondingCustomers) {
        this.respondingCustomers = respondingCustomers;
    }

    public TargetAudience build() {
        return new TargetAudience(identifier, targetCustomers, respondingCustomers);
    }
}
