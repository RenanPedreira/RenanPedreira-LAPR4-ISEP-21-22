package eapli.base.app.backoffice.console.presentation.product;

import eapli.framework.actions.Action;

public class CategoryRegistrationAction implements Action {
    public boolean execute(){
        return new CategoryRegistrationUI().doShow();
    }
}
