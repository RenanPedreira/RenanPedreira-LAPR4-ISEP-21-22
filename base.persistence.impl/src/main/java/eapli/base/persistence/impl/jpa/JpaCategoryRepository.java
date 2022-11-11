package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.productmanagement.domain.Categorya.Alpha;
import eapli.base.productmanagement.domain.Categorya.Category;
import eapli.base.productmanagement.persistence.CategoryRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class JpaCategoryRepository
        extends JpaAutoTxRepository<Category, Alpha, Alpha>
        implements CategoryRepository {

    public JpaCategoryRepository(TransactionalContext autoTx){
        super(autoTx, "String");
    }

    public JpaCategoryRepository(String puname, Map extendedPersistenceProperties){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "String");

    }

    @Override
    public List<Category> getAllCategories() {
        Query q = entityManager().createQuery("SELECT cat FROM Category cat");
        return q.getResultList();
    }
    @Override
    public List<Category> getCategory(String category) {
        Query q = entityManager().createQuery("SELECT cat FROM Category cat " +

                "WHERE cat.category = :category");
        q.setParameter("category", category);
        return q.getResultList();
    }
}
