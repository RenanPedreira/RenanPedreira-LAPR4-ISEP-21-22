package eapli.base.app.backoffice.console.presentation.product;

import eapli.base.productmanagement.application.ViewCatalogController;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Renan Pedreira
 */
public class ViewCatalogUI extends AbstractUI {
    public boolean doShow(){
        ViewCatalogController controller = new ViewCatalogController();
        Integer choice;
        boolean validOption = false;
        List<ProductDTO> products = new ArrayList<>();

        System.out.println("Filters:\n" +
                "1)Category\n" +
                "2)Description\n" +
                "3)Brand\n" +
                "4)Reset filters\n" +
                "0)See filter result\n\n");

        do {
            choice = Console.readInteger("\n\nSelect a filter: ");
            if(choice<0 || choice>4)
                System.out.println("Invalid input");

            if (choice == 1) {
                String category = Console.readLine("\nCategory: ");
                controller.filterByCategory(category);
            }

            if (choice == 2) {
                String description = Console.readLine("\nDescription: ");
                controller.filterByDescription(description);
            }

            if (choice == 3) {
                String brand = Console.readLine("\nBrand: ");
                controller.filterByBrand(brand);
            }

            if (choice == 4) {
                controller.resetFilters();
            }

        } while (choice!=0);

        System.out.println("Sorting methods:\n" +
                "1)Alphabetic\n" +
                "2)Higher prices\n" +
                "3)Lower prices\n\n");

        do {
            choice = Console.readInteger("\n\nSelect a sorting method: ");
            if(choice<1 || choice>3)
                System.out.println("Invalid input");
            else {
                products.addAll(controller.filterResult(choice));
                validOption=true;
            }
        } while (!validOption);


        for (int i = 0; i < products.size(); i++) {
            System.out.println(i + 1 + ")" + products.get(i).showBasicInformation()+"\n");
        }

        do {
            choice = Console.readInteger("\n\nView a product's detail?\n1)Yes\n2)No\n");
            if (choice == 1)
                selectsIndex(products);
            if (choice != 1 && choice != 2)
                System.out.println("Invalid input");
        } while (choice != 2);

        System.out.println("Sorting completed with success");
        return true;
    }

    @Override
    public String headline() {
        return "View Product Catalog";
    }


    public void selectsIndex(List<ProductDTO> list) {
        String input;
        Integer value;

        do {
            input = Console.readLine("\nSelect a product: ");
            value = Integer.valueOf(input);
        } while (value <= 0 || value > list.size());
        System.out.println(list.get(value-1).showAllInformation()); //showAllInfo()
    }
}

