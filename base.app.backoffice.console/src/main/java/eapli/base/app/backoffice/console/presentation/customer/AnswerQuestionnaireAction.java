package eapli.base.app.backoffice.console.presentation.customer;

import eapli.framework.actions.Action;

public class AnswerQuestionnaireAction implements Action {

    @Override
    public boolean execute() {
        return new AnswerQuestionnaireUI().doShow();
    }
}
