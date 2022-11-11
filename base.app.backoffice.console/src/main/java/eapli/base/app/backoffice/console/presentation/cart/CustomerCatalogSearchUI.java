package eapli.base.app.backoffice.console.presentation.cart;

import eapli.base.cartmanagement.application.CustomerCatalogSearchController;
import eapli.base.orderServerAPI.CsvOrderProtocolProxy;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.io.util.Console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerCatalogSearchUI {
    public boolean doShow(CsvOrderProtocolProxy proxy) throws IOException {
        CustomerCatalogSearchController controller = new CustomerCatalogSearchController(proxy);
        Integer choice;
        boolean validOption = false;
        List<ProductDTO> products = new ArrayList<>();

        System.out.println("\n***** Product Catalog *****\n");

        Integer filterOption=0;
        do {
            System.out.println("Filters:\n" +
                    "1)Category\n" +
                    "2)Description\n" +
                    "3)Brand\n" +
                    "4)Reset filters\n" +
                    "0)See filter result\n\n");

            do {
                choice = Console.readInteger("\n\nSelect a filter: ");
                if (choice < 0 || choice > 4)
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

            } while (choice != 0);

            System.out.println("Sorting methods:\n" +
                    "1)Alphabetic\n" +
                    "2)Higher prices\n" +
                    "3)Lower prices\n\n");

            do {
                choice = Console.readInteger("\n\nSelect a sorting method: ");
                if (choice < 1 || choice > 3)
                    System.out.println("Invalid input");
                else {
                    products = controller.filterResult(choice);
                    validOption = true;
                }
            } while (!validOption);

            for (int i = 0; i < products.size(); i++) {
                System.out.println(i + 1 + ")" + products.get(i).showBasicInformation() + "\n");
            }

            do {
                choice = Console.readInteger("\n\nAdd a product to the shopping cart?\n1)Yes\n2)No\n");
                if (choice == 1) {
                    ProductDTO product = selectsIndex(products);
                    Integer quantity = Console.readInteger("Product quantity: ");
                    controller.requestCartItemAddition(product, quantity);
                }
                if (choice != 1 && choice != 2)
                    System.out.println("Invalid input");
            } while (choice != 2);

            filterOption=Console.readInteger("Get another filtered product catalog?\n1)Yes\n2)No");
        }while(filterOption!=2);

        System.out.println(controller.cartCurrentProducts());

        return true;
    }

    public ProductDTO selectsIndex(List<ProductDTO> list) {
        Integer input;

        do {
            input = Console.readInteger("\nSelect a product: ");
        } while (input <= 0 || input > list.size());

        return list.get(input-1);
    }
}
