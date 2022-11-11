package eapli.base.app.user.console.presentation;

import eapli.framework.actions.Action;

public class ViewStatusOfOpenOrdersAction implements Action {

    @Override
    public boolean execute() {
        return new ViewStatusOfOpenOrdersUI().doShow();
    }
}
