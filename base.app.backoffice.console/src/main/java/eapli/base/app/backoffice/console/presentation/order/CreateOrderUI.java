package eapli.base.app.backoffice.console.presentation.order;


import eapli.base.ordermanagement.application.CreateOrderForClientController;
import eapli.base.ordermanagement.dto.OrderItemDTO;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.service.ProductFilterService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Renan Pedreira
 */
public class CreateOrderUI {
    public boolean doShow() {
        CreateOrderForClientController controller = new CreateOrderForClientController();
        Integer choice;
        boolean validOption = false;
        List<ProductDTO> products = new ArrayList<>();

        System.out.println("***** Order Information *****\n");
        /** Source Channel */
        String sourceChannel = Console.readLine("\nSource Channel: ");

        /** Comment */
        String comment = Console.readLine("\nComment: ");

        /** Payment Method */
        String paymentMethod = Console.readLine("\nPayment Method: ");

        /** Client's email */
        String clientEmail = Console.readLine("\nClient's email: ");

        /** Billing Address */
        String[] billingAddress = new String[3];
        billingAddress[0] = Console.readLine("\nBilling address' street name: ");
        billingAddress[1] = Console.readLine("\nBilling address' city name: ");
        billingAddress[2] = Console.readLine("\nBilling address' door name: ");

        /** Delivering Address */
        String[] deliveringAddress = new String[3];
        deliveringAddress[0] = Console.readLine("\nDelivering address' street name: ");
        deliveringAddress[1] = Console.readLine("\nDelivering address' city name: ");
        deliveringAddress[2] = Console.readLine("\nDelivering address' door name: ");

        System.out.println(controller.createOrderForClient(sourceChannel, comment, paymentMethod, clientEmail, billingAddress, deliveringAddress));

        System.out.println("\n***** Order's Items *****\n");

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
                choice = Console.readInteger("\n\nAdd a product to the order?\n1)Yes\n2)No\n");
                if (choice == 1) {
                    ProductDTO product = selectsIndex(products);
                    Integer quantity = Console.readInteger("Product quantity: ");
                    controller.createOrderItem(product, quantity);
                }
                if (choice != 1 && choice != 2)
                    System.out.println("Invalid input");
            } while (choice != 2);

            filterOption=Console.readInteger("Get another filtered product catalog?\n1)Yes\n2)No");
        }while(filterOption!=2);

        List<OrderItemDTO> itemList =controller.getAllOrderItensAdded();
        for(OrderItemDTO item : itemList)
            System.out.println(item.showInfo());

        controller.confirmOrder();

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
