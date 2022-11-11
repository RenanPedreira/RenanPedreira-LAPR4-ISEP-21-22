package eapli.base.productmanagement.dto;

import eapli.base.productmanagement.domain.Categorya.Category;


import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    public List<CategoryDto> toDTO(List<Category> categoryList){
        List<CategoryDto> listCategoryDTO = new ArrayList<>();
        for(Category c : categoryList) {
            CategoryDto categoryDTO = new CategoryDto(c);
            listCategoryDTO.add(categoryDTO);
        }
        return listCategoryDTO;
    }
}
