package eapli.base.app.backoffice.console.presentation.warehouse;

import eapli.base.warehousemanagement.presentation.SetWarehousePlantUI;
import eapli.framework.actions.Action;

public class SetWarehousePlantAction implements Action {

    @Override
    public boolean execute() {
        return new SetWarehousePlantUI().show();
    }
}
