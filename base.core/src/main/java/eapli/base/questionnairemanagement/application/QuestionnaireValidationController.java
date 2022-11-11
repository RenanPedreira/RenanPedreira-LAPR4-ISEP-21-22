package eapli.base.questionnairemanagement.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireValidationController {
    private QuestionnaireValidationService service = new QuestionnaireValidationService();

    public void validateQuestionnaire(String filePath) throws IOException {
        service.validateQuestionnaire(filePath);
    }
}
