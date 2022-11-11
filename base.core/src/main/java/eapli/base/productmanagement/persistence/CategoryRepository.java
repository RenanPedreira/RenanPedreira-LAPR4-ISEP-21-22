package eapli.base.productmanagement.persistence;

import eapli.base.productmanagement.domain.Categorya.Alpha;
import eapli.base.productmanagement.domain.Categorya.Category;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;

public interface CategoryRepository extends DomainRepository<Alpha, Category> {
    List<Category> getAllCategories();


    List<Category> getCategory(String category);
}
