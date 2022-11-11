package eapli.base.productmanagement.persistence;

import eapli.base.productmanagement.domain.Producto.InternalCode;
import eapli.base.productmanagement.domain.Producto.Product;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

/**
 *
 * @author Renan Pedreira
 */
public interface ProductRepository extends DomainRepository<InternalCode, Product> {
    List<Product> getAllProducts();
    List<Product> getProductsFromCategory(String category);
    List<Product> getProductsFromDescription(String description);
    List<Product> getProductsFromBrand(String brand);
    List<Product> getProductByID(String id);
}
