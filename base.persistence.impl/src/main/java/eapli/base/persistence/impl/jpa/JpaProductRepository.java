package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.productmanagement.domain.Producto.InternalCode;
import eapli.base.productmanagement.domain.Producto.Product;
import eapli.base.productmanagement.persistence.ProductRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;


public class JpaProductRepository
        extends JpaAutoTxRepository<Product, InternalCode, InternalCode>
        implements ProductRepository {

    public JpaProductRepository(TransactionalContext autoTx){
        super(autoTx, "InternalCode");
    }

    public JpaProductRepository(String puname, Map extendedPersistenceProperties){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "InternalCode");
    }

    @Override
    public List<Product> getAllProducts() {
        Query q = entityManager().createQuery("SELECT prod FROM Product prod");
        return q.getResultList();
    }

    @Override
    public List<Product> getProductsFromCategory(String category) {
        Query q = entityManager().createQuery("SELECT prod FROM Product prod " +
                                                 "JOIN prod.category cat " +
                                                 "WHERE cat.description.description = :category");
        q.setParameter("category", category);
        return q.getResultList();
    }

    @Override
    public List<Product> getProductsFromDescription(String description) {
        StringBuilder d = new StringBuilder();
        d.append("%");
        d.append(description);
        d.append("%");

        Query q = entityManager().createQuery("SELECT prod FROM Product prod " +
                                                 "WHERE prod.shortDescription.shortDescription LIKE :description " +
                                                 "OR prod.longDescription.longDescription LIKE :description " +
                                                 "OR prod.technicalDescription.techinalDescription LIKE :description");

        q.setParameter("description", d.toString());
        return q.getResultList();
    }

    @Override
    public List<Product> getProductsFromBrand(String brand) {
        Query q = entityManager().createQuery("SELECT prod FROM Product prod " +
                                                 "WHERE prod.brand.brandName = :brand");
        q.setParameter("brand", brand);
        return q.getResultList();
    }

    @Override
    public List<Product> getProductByID(String id) {
        Query q = entityManager().createQuery("SELECT prod FROM Product prod " +
                "WHERE prod.id.internalCode = :idC");
        q.setParameter("idC", id);
        return q.getResultList();
    }
}
