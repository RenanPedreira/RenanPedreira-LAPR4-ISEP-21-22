package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.cartmanagement.domain.Cart;
import eapli.base.cartmanagement.persistence.CartRepository;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class JpaCartRepository
        extends JpaAutoTxRepository<Cart, Integer, Integer>
        implements CartRepository {

    public JpaCartRepository(TransactionalContext autoTx){
        super(autoTx, "String");
    }

    public JpaCartRepository(String puname, Map extendedPersistenceProperties){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "String");
    }

    @Override
    public List<Cart> getCartByCustomer(String customerEmail) {
        Query q = entityManager().createQuery("SELECT cart FROM Cart cart " +
                //"JOIN cart.customer customer " +
                "WHERE cart.customer.systemUser.email.email = :customerEmail");
        q.setParameter("customerEmail", customerEmail);
        return q.getResultList();
    }
}
