package eapli.base.cartmanagement.persistence;

import eapli.base.cartmanagement.domain.Cart;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface CartRepository extends DomainRepository<Integer, Cart> {

    List<Cart> getCartByCustomer(String customerEmail);
}
