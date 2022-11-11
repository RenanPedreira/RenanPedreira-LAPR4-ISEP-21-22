package eapli.base.customermanagement.domain.dto;

import eapli.base.questionnairemanagement.dto.QuestionnaireDTO;

import java.util.List;

public class HistoryDTO {
    private String identifier;
    private List<QuestionnaireDTO> answeredQuestionnairesDTO;
    private List<QuestionnaireDTO> unansweredQuestionnairesDTO;

    public HistoryDTO(String identifier, List<QuestionnaireDTO> answeredQuestionnairesDTO, List<QuestionnaireDTO> unansweredQuestionnairesDTO) {
        this.identifier = identifier;
        this.answeredQuestionnairesDTO = answeredQuestionnairesDTO;
        this.unansweredQuestionnairesDTO = unansweredQuestionnairesDTO;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<QuestionnaireDTO> getAnsweredQuestionnairesDTO() {
        return answeredQuestionnairesDTO;
    }

    public List<QuestionnaireDTO> getUnansweredQuestionnairesDTO() {
        return unansweredQuestionnairesDTO;
    }
}
