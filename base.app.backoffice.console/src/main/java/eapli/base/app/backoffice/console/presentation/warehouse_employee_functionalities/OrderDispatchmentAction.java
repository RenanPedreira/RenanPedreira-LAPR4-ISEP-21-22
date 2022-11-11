package eapli.base.app.backoffice.console.presentation.warehouse_employee_functionalities;

import eapli.framework.actions.Action;

public class OrderDispatchmentAction implements Action {
    @Override
    public boolean execute() {
        return new OrderDispatchmentUI().show();
    }
}
