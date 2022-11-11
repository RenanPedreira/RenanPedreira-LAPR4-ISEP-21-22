package eapli.base.productmanagement.dto;

import eapli.base.productmanagement.domain.Categorya.Category;


public class CategoryDto {


    private String alpha;
    private String description;

    private Category superCategory;

    public CategoryDto(Category c){

        if (c.getSuperCategory() == null){
            //this.category = c.getCategory();
            this.alpha = c.getAlpha();
            this.description = c.getDescription();
        }else{
            //this.category = c.getCategory();
            this.superCategory = c.getSuperCategory();
            this.alpha = c.getAlpha();
            this.description = c.getDescription();
        }

    }

    public String getAlpha()
    {
        return this.alpha;
    }


    public String show() {
        if(this.superCategory != null) {
            return "CategoryDto{" +
                    "category='" + alpha + '\'' +
                    ", description=" + description +
                    ", superCategory=" + superCategory +
                    '}';
        }
        return "CategoryDto{" +
                "category='" + alpha + '\'' +
                ", description=" + description +
                ", superCategory= This category have no Super Category";
    }
}

