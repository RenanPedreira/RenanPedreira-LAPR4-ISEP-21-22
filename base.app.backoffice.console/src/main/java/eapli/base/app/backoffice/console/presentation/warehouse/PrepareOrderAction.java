package eapli.base.app.backoffice.console.presentation.warehouse;

import eapli.base.warehousemanagement.presentation.PrepareOrderUI;
import eapli.framework.actions.Action;

public class PrepareOrderAction implements Action {

    @Override
    public boolean execute() {
        return new PrepareOrderUI().doShow();
    }
}
