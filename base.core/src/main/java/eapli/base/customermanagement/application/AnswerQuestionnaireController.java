package eapli.base.customermanagement.application;

import eapli.base.questionnairemanagement.dto.QuestionDTO;
import eapli.base.questionnairemanagement.dto.QuestionnaireDTO;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AnswerQuestionnaireController {
    private AnswerQuestionnaireService service;

    public AnswerQuestionnaireController() {
        service = new AnswerQuestionnaireService();
    }

    public List<QuestionnaireDTO> listAssignedUnansweredQuestionnaire() {
        return service.listAssignedUnansweredQuestionnaire();
    }

    public void validateAndPersistAnswers(QuestionnaireDTO questionnaireDTO,
                                          Map<QuestionDTO, LinkedList<String>> answersPerQuestionDTO) {
        service.validateAndPersistAnswers(questionnaireDTO, answersPerQuestionDTO);
    }
}
