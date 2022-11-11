package eapli.base.service;

import eapli.base.cartmanagement.domain.Cart;
import eapli.base.cartmanagement.domain.CartItem;
import eapli.base.cartmanagement.dto.CartItemDTO;
import eapli.base.cartmanagement.persistence.CartRepository;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Producto.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.persistence.ProductRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AddProductToCartService {

    private TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private CartRepository cartRepository = PersistenceContext.repositories().cartRepository(txCtx);
    private ProductRepository productRepository = PersistenceContext.repositories().products(txCtx);


    //ADD CART ITEM
    public String addProductToCart(String email, String productCode, String quantityS){
        Cart cart = cartRepository.getCartByCustomer(trim(email)).get(0);
        Product product = productRepository.getProductByID(trim(productCode)).get(0);
        Integer quantity = Integer.valueOf(trim(quantityS));

        /*
        txCtx.beginTransaction();
        if(validateProduct(cart, product)) {
            cart.addProductToCart(product, quantity);
            cartRepository.save(cart);
            txCtx.commit();
        }
         */
        txCtx.beginTransaction();
        cart.addProductToCart(product, quantity);
        cartRepository.save(cart);
        txCtx.commit();
        txCtx.close();

        return String.format("\nAdded %d copies of the product %s to your cart!\n", quantity, product.getName());
    }

    //SEE CART ITEMS
    public List<CartItem> cartCurrentProducts(String email){
        Cart cart = cartRepository.getCartByCustomer(trim(email)).get(0);

        return cart.items();
    }

    public List<CartItemDTO> cartCurrentProductsDTO(String email){
        Cart cart = cartRepository.getCartByCustomer(trim(email)).get(0);

        List<CartItem> items = cart.items();
        return toDTO(items);
    }

    public List<CartItemDTO> toDTO(List<CartItem> list){
        List<CartItemDTO> listDTO = new ArrayList<>();
        for(CartItem cartItem:list)
            listDTO.add(cartItem.toDTO());
        return listDTO;
    }

    public boolean validateProduct(Cart cart, Product product){
        List<CartItem> items = cart.items();
        for(CartItem item : items){
            if(item.product().getInternalCode().compareTo(product.getInternalCode())==0)
                return false;
        }
        return true;
    }

    public String trim(String str){
        StringBuilder str1 = new StringBuilder(str);
        str1.deleteCharAt(str.length()-1);
        str1.deleteCharAt(0);
        return str1.toString();
    }
}