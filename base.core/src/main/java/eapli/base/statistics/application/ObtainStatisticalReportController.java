package eapli.base.statistics.application;

import eapli.base.questionnairemanagement.dto.QuestionnaireDTO;

import java.util.List;

public class ObtainStatisticalReportController {
    private ObtainStatisticalReportService service;

    public ObtainStatisticalReportController() {
        service = new ObtainStatisticalReportService();
    }

    public List<QuestionnaireDTO> listAllAnsweredQuestionnaires() {
        return service.listAllAnsweredQuestionnaires();
    }

    public void validateAndCompileQuestionnaire(QuestionnaireDTO questionnaireDTO) {
        service.validateAndCompile(questionnaireDTO);
    }
}
