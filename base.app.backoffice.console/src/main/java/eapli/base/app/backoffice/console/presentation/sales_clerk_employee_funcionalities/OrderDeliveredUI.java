package eapli.base.app.backoffice.console.presentation.sales_clerk_employee_funcionalities;

import eapli.base.ordermanagement.application.OrderDeliveredController;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDeliveredUI extends AbstractUI {

    private static final int EXIT_OPTION = 0;
    private static final int INITIAL_OPTION = 1;

    private static OrderDeliveredController controller = new OrderDeliveredController();


    @Override
    protected boolean doShow() {
        int cnt = 1;
        List<OrderDTO> orderDTOList = controller.listAllDispatchedOrders();
        Map<OrderDTO,OrderDTO> auxMap = new HashMap<>();

        for(OrderDTO orderDTO : orderDTOList) {
            System.out.printf("#%d - %s\n\n", cnt, orderDTO);

            cnt++;
        }

        int option;

        do {
            System.out.printf("Please select the Orders whose Status are to be updated:\n\nOPTIONS: [%d - %d]\nEXIT OPTION: %d\n",
                    INITIAL_OPTION,
                    orderDTOList.size(),
                    EXIT_OPTION);

            option = Console.readOption(INITIAL_OPTION,orderDTOList.size(),EXIT_OPTION);

            System.out.println();

            if(option != EXIT_OPTION) {
                if(!auxMap.containsKey(orderDTOList.get(option-1)))
                    auxMap.put(orderDTOList.get(option - 1), orderDTOList.get(option - 1));
                else
                    System.out.println("Option already selected. \nPlease select a different option.");

            }



        }while(option != EXIT_OPTION);

        controller.updateStatus(auxMap.keySet());

        System.out.println("Orders status have been successfully updated; ");

        return false;
    }

    @Override
    public String headline() {
        return "Update Orders for Delivered";
    }
}
