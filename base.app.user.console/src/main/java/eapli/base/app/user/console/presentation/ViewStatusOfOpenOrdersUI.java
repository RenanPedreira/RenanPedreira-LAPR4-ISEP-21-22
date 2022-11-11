package eapli.base.app.user.console.presentation;

import eapli.base.app.user.console.viewingstatus.application.ViewStatusOfOpenOrdersController;
import eapli.base.app.user.console.viewingstatus.csvprotocol.client.FailedRequestException;
import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class ViewStatusOfOpenOrdersUI extends AbstractUI {

    private ViewStatusOfOpenOrdersController controller = new ViewStatusOfOpenOrdersController();

    @Override
    protected boolean doShow() {

        try {
            String ordersList = null;
            try {
                this.controller.connectToServer();
                ordersList = this.controller.getClientOrders();
            } catch (FailedRequestException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (ordersList == null) {
                System.out.println("There are no open orders yet!");
            } else {
                System.out.println("Here are your orders and their status: \n");
                System.out.println(ordersList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String headline() {
        return null;
    }
}
