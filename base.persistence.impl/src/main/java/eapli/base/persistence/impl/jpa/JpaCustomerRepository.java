package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.common.domain.model.Identifier;
import eapli.base.customermanagement.repositories.CustomerRepository;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Data Structure which is responsible for interacting with the database regarding solely the Aggregate "Customer".
 *
 * @author Gonçalo Monteiro
 */
public class JpaCustomerRepository extends JpaAutoTxRepository<Customer, Identifier, Identifier> implements CustomerRepository {
    private static final String IDENTITY_FIELD = "Identifier";

    public JpaCustomerRepository(TransactionalContext context) {
        super(context, IDENTITY_FIELD);
    }

    public JpaCustomerRepository(String persistanceUnitName) {
        super(persistanceUnitName, Application.settings().getExtendedPersistenceProperties(), IDENTITY_FIELD);
    }

    public Customer findByIdentifier(Identifier identifier) {
        Query query = entityManager().createQuery("SELECT customer FROM Customer customer WHERE customer.identifier.identifier = :identifier");

        query.setParameter("identifier", identifier.getIdentifier());

        List<Customer> objectList = query.getResultList();

        return objectList.isEmpty() ? null : objectList.get(0);
    }

    @Override
    public List<Customer> findByEmail(String email) {
        Query query = entityManager().createQuery("SELECT customer FROM Customer customer WHERE customer.systemUser.email.email = :email");

        query.setParameter("email", email);

        return query.getResultList();
    }
}
