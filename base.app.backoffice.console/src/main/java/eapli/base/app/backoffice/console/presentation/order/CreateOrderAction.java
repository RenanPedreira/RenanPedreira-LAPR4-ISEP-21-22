package eapli.base.app.backoffice.console.presentation.order;

import eapli.framework.actions.Action;

/**
 *
 * @author Renan Pedreira
 */
public class CreateOrderAction implements Action {
    public boolean execute(){
        return new CreateOrderUI().doShow();
    }
}
