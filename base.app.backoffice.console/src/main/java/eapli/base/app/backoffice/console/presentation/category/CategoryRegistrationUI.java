package eapli.base.app.backoffice.console.presentation.category;

import eapli.base.productmanagement.application.CategoryRegistrationController;
import eapli.base.productmanagement.dto.CategoryDto;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class CategoryRegistrationUI extends AbstractUI {
    @Override
    protected boolean doShow() {

        CategoryRegistrationController controller = new CategoryRegistrationController();
        CategoryDto dto1;



        final String alpha = Console.readLine(" Alphanumeric code:\n ");
        final String description = Console.readLine("Description:\n");
        final Integer choice;



        int n=0;
        for(CategoryDto dto:controller.getProductCategories())
        {
            n++;
            System.out.println(n + " " + dto + " \n");
        }
        System.out.println("Press 0 if the category have no super category");


        choice = Console.readInteger("Choose one\n");

        if(choice == 0)
            dto1 = null;
        else
            dto1 = controller.getProductCategories().get(choice-1);


        try {
            controller.specifyCategory(alpha,description,dto1);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a category which already exists in the database");
        }
            return false;
    }

    @Override
    public String headline() {
            return "Add Category";

    }
}