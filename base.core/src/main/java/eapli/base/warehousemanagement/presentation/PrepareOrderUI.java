package eapli.base.warehousemanagement.presentation;

import eapli.base.agvmanagement.dto.AGVDTO;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.base.warehousemanagement.application.PrepareOrderController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.SelectWidget;

import java.util.List;

public class PrepareOrderUI {

    private final PrepareOrderController controller = new PrepareOrderController();

    public boolean doShow() {
        try{
            List<OrderDTO> orderList = this.controller.listOrdersToBePrepared();
            int input;

            do {
                for (int i = 0; i < orderList.size(); i++) {
                    System.out.println(i + 1 + ") " + orderList.get(i).showInformation() + "\n");
                }
                input = Console.readInteger("Please select an order: \n");
            } while (input <= 0 || input > orderList.size());

            OrderDTO selectedOrder = orderList.get(input - 1);

            List<AGVDTO> agvList = this.controller.listAvailableAGVS(selectedOrder);
            SelectWidget<AGVDTO> selector = new SelectWidget<>("Please select an AGV: \n", agvList);
            selector.show();
            AGVDTO selectedAGV = selector.selectedElement();

            if (this.controller.assignment(selectedOrder, selectedAGV)){
                System.out.println("The order is being prepared!");
            }else {
                System.out.println("Something went wrong in the assignment!");
            }

        }catch (Exception e){
            System.out.println("Something went wrong!");
        }
        return true;
    }
}


