package eapli.base.app.backoffice.console.presentation.customer;

import eapli.base.app.user.console.presentation.myuser.UserDataWidget;
import eapli.base.common.util.Triple;
import eapli.base.customermanagement.application.RegisterCustomerController;
import eapli.base.customermanagement.domain.dto.CustomerDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 * Data Structure which is responsible for interacting with the Sales Clerk and coordinating the Use Case 1003;
 *
 * @author Gonçalo Monteiro
 */
public class RegisterCustomerUI extends AbstractUI {
    private static final int EXIT_OPTION = 0;
    private static final int DELIVERY_ADDRESS_OPTION = 1;
    private static final int BILLING_ADDRESS_OPTION = 2;
    private static final String DELIVERY_ADDRESS = "DELIVERY_ADDRESS";
    private static final String BILLING_ADDRESS = "BILLING_ADDRESS";
    private static final RegisterCustomerController controller = new RegisterCustomerController();

    @Override
    protected boolean doShow() {
        UserDataWidget widget = new UserDataWidget();

        widget.show();

        String customerPhoneNumber = Console.readLine("Please insert user's phone number: ");
        String customerVATIdentification = Console.readLine("Please insert user's VAT identification");
        LocalDate customerBirthdate = null;
        String customerGender = null;
        Map<Triple<String, String, Integer>, String> addressList = new HashMap<>();

        String option = Console.readLine("\nDo you want to add the user's birthdate [Y|N]:\n");

        switch (option) {
            case "Y":
                customerBirthdate = Console.readDate("Please insert user's birthdate: ")
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                break;
            case "N":
                break;
            default:
                System.out.printf("Invalid option inserted.\nCustomer registration aborted.");

                break;
        }

        option = Console.readLine("\nDo you want to add the user's gender [Y|N]:\n");

        switch (option) {
            case "Y":
                customerGender = Console.readLine("Please insert user's gender: ");
                break;
            case "N":
                break;
            default:
                System.out.printf("Invalid option inserted.\nCustomer registration aborted.");

                break;
        }

        option = Console.readLine("\nDo you want to associate the user to an address [Y|N]:\n");

        Switch_Case:
        switch (option) {
            case "Y":
                do {
                    String streetName = Console.readLine("Please insert the name of the street: ");
                    int doorNumber = Console.readInteger("Please insert the number of the door: ");
                    String cityName = Console.readLine("Please insert the name of the city: ");

                    String addressType = null;

                    System.out.println("Is the address a billing address or a delivery address?\n1) Delivery Address\n2)Billing Address\n");

                    int option1 = Console.readOption(DELIVERY_ADDRESS_OPTION, BILLING_ADDRESS_OPTION, EXIT_OPTION);

                    switch (option1) {
                        case DELIVERY_ADDRESS_OPTION:
                            addressType = DELIVERY_ADDRESS;

                            break;
                        case BILLING_ADDRESS_OPTION:
                            addressType = BILLING_ADDRESS;

                            break;
                        case EXIT_OPTION:
                            System.out.println("Insertion of address has been canceled.");
                            break Switch_Case;
                    }

                    addressList.put(new Triple<>(streetName, cityName, doorNumber), addressType);
                } while (Console.readLine("Do you want to add more addresses? [Y|N]").equals("Y"));

                break;
            case "N":
                break;
            default:
                System.out.printf("Invalid option inserted.\nCustomer registration aborted.");

                break;
        }

        CustomerDTO customerDTO = controller.registerCustomer(widget.firstName(),
                widget.lastName(),
                widget.email(),
                customerPhoneNumber,
                customerVATIdentification,
                customerBirthdate,
                customerGender,
                addressList);

        System.out.printf("\n\tNew Customer Information\nFirst Name: %s\nSurname: %s\nEmail: %s\nPhone Number: %s\nVAT Identification: %s\n",
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getEmail(),
                customerDTO.getPhoneNumber(),
                customerDTO.getIdVAT());

        if (customerDTO.getBirthdate() != null)
            System.out.println("Birthdate: " + customerDTO.getBirthdate().toString());
        if (customerDTO.getGender() != null)
            System.out.println("Gender: " + customerDTO.getGender());
        if (!customerDTO.getAddressList().isEmpty()) {
            for (Map.Entry<Triple<String, String, Integer>, String> entry : customerDTO.getAddressList().entrySet())
                System.out.printf("\tADDRESS\nStreet name: %s\nDoor number: %d\nCity Name: %s\nAddress Type: %s\n",
                        entry.getKey().getFirstElement(),
                        entry.getKey().getThirdElement(),
                        entry.getKey().getSecondElement(),
                        entry.getValue());

        }

        option = Console.readLine("\nPlease confirm new customer data [Y|N]:\n");

        switch (option) {
            case "Y":
                controller.persistCustomer(customerDTO);

                break;
            case "N":

                break;
            default:
                System.out.printf("Invalid option inserted.\nCustomer registration aborted.");

                break;
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register manually Customer";
    }
}
