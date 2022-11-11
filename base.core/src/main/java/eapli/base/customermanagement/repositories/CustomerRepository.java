package eapli.base.customermanagement.repositories;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.common.domain.model.Identifier;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.List;

public interface CustomerRepository extends DomainRepository<Identifier, Customer> {
    public List<Customer> findByEmail(String mail);
}
