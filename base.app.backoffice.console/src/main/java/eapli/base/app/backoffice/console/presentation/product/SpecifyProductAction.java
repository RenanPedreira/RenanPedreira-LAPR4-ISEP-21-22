package eapli.base.app.backoffice.console.presentation.product;

import eapli.framework.actions.Action;

import java.io.IOException;


public class SpecifyProductAction implements Action {
    public boolean execute(){
        return new SpecifyProductUI().doShow();
    }

}
