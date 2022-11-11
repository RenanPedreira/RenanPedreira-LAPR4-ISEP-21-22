package eapli.base.productmanagement.domain.Categorya;

import eapli.framework.domain.model.DomainFactory;

public class CategoryFactory implements DomainFactory<Category> {

    private Alpha alpha;
    private Description description;
    private Category superCategory;
    private Category category;



    public CategoryFactory alpha(final Alpha alpha)
    {
        this.alpha = alpha;
        return this;
    }

    public CategoryFactory description(final Description description)
    {
        this.description = description;
        return this;
    }

    public CategoryFactory superCategory(final Category superCategory)
    {
        this.superCategory = superCategory;
        return this;
    }

    private Category buildOrThrow() {
        if(category != null) {
            return category;
        } else if(alpha != null & description != null){
            category = new Category(alpha,description,superCategory);
        }
        return category;
    }


    @Override
    public Category build() {
        final Category cat = buildOrThrow();
        return cat;
    }
}
