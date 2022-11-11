package eapli.base.productmanagement.service;

import eapli.base.infrastructure.persistence.PersistenceContext;

import eapli.base.productmanagement.domain.Categorya.Alpha;
import eapli.base.productmanagement.domain.Categorya.Category;
import eapli.base.productmanagement.domain.Categorya.CategoryFactory;
import eapli.base.productmanagement.domain.Categorya.Description;
import eapli.base.productmanagement.dto.CategoryDto;
import eapli.base.productmanagement.persistence.CategoryRepository;
import eapli.framework.domain.repositories.TransactionalContext;

public class CategoriesService {
    private final CategoryRepository repo = PersistenceContext.repositories().categories();


    public void buildCategory(String alpha, String description, CategoryDto superCategory) {
        Alpha alphaCode = new Alpha(alpha);
        Description catDescrition = new Description(description);
        Category superCat = getRealCategory(superCategory);

        final var category = new CategoryFactory().alpha(new Alpha(alpha))
                .description(new Description(description)).superCategory(superCat). build();

        repo.save(category);
    }

    public Category getRealCategory(CategoryDto categoryDto) {
        if (categoryDto == null)
            return null;

        Alpha alpha = new Alpha(categoryDto.getAlpha());
        return repo.ofIdentity(alpha).get();
    }


}
