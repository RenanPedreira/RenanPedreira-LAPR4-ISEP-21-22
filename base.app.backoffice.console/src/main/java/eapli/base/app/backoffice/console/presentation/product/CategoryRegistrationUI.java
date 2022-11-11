package eapli.base.app.backoffice.console.presentation.product;

import eapli.base.productmanagement.application.CategoryRegistrationController;
import eapli.base.productmanagement.dto.CategoryDto;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRegistrationUI extends AbstractUI {
    @Override
    protected boolean doShow() {

        CategoryRegistrationController controller = new CategoryRegistrationController();
        List<CategoryDto> list = new ArrayList<>();
        CategoryDto dto1;

        final String alpha = Console.readLine(" Alphanumeric code:\n ");
        final String description = Console.readLine("Description:\n");
        final Integer choice;

        int n=0;

        list.addAll(controller.getProductCategories());
        for(CategoryDto dto: list)
        {
            n++;
            System.out.println(n + " " + dto.show() + " \n");
        }
        System.out.println("Press 0 if the category have no super category");


        choice = Console.readInteger("Choose one\n");

        if(choice == 0)
            dto1 = null;
        else
            dto1 = list.get(choice-1);

        try {
            controller.specifyCategory(alpha,description,dto1);

        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a category which already exists in the database");
            return false;
        }
        return true;
    }

    @Override
    public String headline() {
        return "Add Category";
    }
}