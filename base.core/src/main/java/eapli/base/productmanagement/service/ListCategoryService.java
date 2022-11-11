package eapli.base.productmanagement.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Categorya.Category;
import eapli.base.productmanagement.dto.CategoryDto;
import eapli.base.productmanagement.dto.CategoryMapper;
import eapli.base.productmanagement.persistence.CategoryRepository;
import eapli.framework.domain.repositories.TransactionalContext;

import java.util.List;

public class ListCategoryService {

    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final CategoryRepository repo = PersistenceContext.repositories().categories(txCtx);


    public List<CategoryDto> productCategories() {
        List<Category> categoryList = repo.getAllCategories();
        CategoryMapper map = new CategoryMapper();
        return map.toDTO(categoryList);
    }

    public Category getRealCategory(CategoryDto categoryDto) {

        for (Category c: repo.getAllCategories()
             ) {
            if(c.getAlpha().equals(categoryDto.getAlpha())){
                return c;
            }
        }
        return  null;
    }


}
