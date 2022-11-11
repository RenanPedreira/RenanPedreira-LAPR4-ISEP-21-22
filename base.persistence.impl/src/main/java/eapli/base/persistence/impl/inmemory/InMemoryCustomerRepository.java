package eapli.base.persistence.impl.inmemory;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.common.domain.model.Identifier;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.List;

public class InMemoryCustomerRepository extends InMemoryDomainRepository<Customer, Identifier> implements CustomerRepository {
    @Override
    public List<Customer> findByEmail(String mail) {
        return null;
    }
}
