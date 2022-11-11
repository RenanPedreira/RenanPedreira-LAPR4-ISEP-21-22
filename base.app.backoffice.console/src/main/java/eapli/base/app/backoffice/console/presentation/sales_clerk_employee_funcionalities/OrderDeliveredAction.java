package eapli.base.app.backoffice.console.presentation.sales_clerk_employee_funcionalities;


import eapli.framework.actions.Action;

import javax.swing.*;

public class OrderDeliveredAction implements Action {

     @Override
    public boolean execute() {return new OrderDeliveredUI().show();}
}
