package eapli.base.productmanagement.application;

import eapli.base.productmanagement.domain.Categorya.Category;
import eapli.base.productmanagement.dto.CategoryDto;
import eapli.base.productmanagement.service.CategoriesService;
import eapli.base.productmanagement.service.ListCategoryService;

import java.util.List;

public class CategoryRegistrationController {

    ListCategoryService svcCategory = new ListCategoryService();
    CategoriesService csCategories = new CategoriesService();

    public List<CategoryDto> getProductCategories(){
        return svcCategory.productCategories();
    }

    public void specifyCategory(String alpha, String description, CategoryDto category ) {
        csCategories.buildCategory(alpha,description, category);
    }

    public Category getOk(CategoryDto categoryDto){
        return svcCategory.getRealCategory(categoryDto);
    }





}