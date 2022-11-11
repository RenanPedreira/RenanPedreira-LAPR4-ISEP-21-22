package eapli.base.app.backoffice.console.presentation.questionnaire;

import eapli.base.common.util.SelectFileAction;
import eapli.base.questionnairemanagement.application.QuestionnaireValidationController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class QuestionnaireValidationUI extends AbstractUI {
    @Override
    protected boolean doShow() {
        QuestionnaireValidationController controller = new QuestionnaireValidationController();
        String filePath = SelectFileAction.selectTextFile();

        try {
            controller.validateQuestionnaire(filePath);
            System.out.println("Validation of Questionnaire successful.");
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return true;
    }

    @Override
    public String headline() {
        return "Questionnaire Validation";
    }
}
