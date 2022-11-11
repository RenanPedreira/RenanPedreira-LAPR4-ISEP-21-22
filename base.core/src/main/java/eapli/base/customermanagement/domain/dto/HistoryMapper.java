package eapli.base.customermanagement.domain.dto;

import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.customermanagement.domain.model.History;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.base.questionnairemanagement.dto.QuestionnaireDTO;
import eapli.base.questionnairemanagement.dto.QuestionnaireMapper;

import java.util.ArrayList;
import java.util.List;

public class HistoryMapper {
    public static HistoryDTO toDTO(String identifier,
                                   List<QuestionnaireDTO> answeredQuestionnairesDTO,
                                   List<QuestionnaireDTO> unansweredQuestionnairesDTO) {
        return new HistoryDTO(identifier, answeredQuestionnairesDTO, unansweredQuestionnairesDTO);
    }

    public static HistoryDTO toDTO(History history) {
        List<QuestionnaireDTO> answeredQuestionnaires = new ArrayList<>();
        List<QuestionnaireDTO> unansweredQuestionnaires = new ArrayList<>();

        if (history != null) {
            for (Questionnaire questionnaire : history.answeredQuestionnaires())
                answeredQuestionnaires.add(QuestionnaireMapper.toDTO(questionnaire));

            for (Questionnaire questionnaire : history.unansweredQuestionnaires())
                unansweredQuestionnaires.add(QuestionnaireMapper.toDTO(questionnaire));
        }

        return new HistoryDTO(history == null ? new IdentifierGenerator().generateIdentifier().getIdentifier() : history.getIdentifier().getIdentifier(),
                answeredQuestionnaires,
                unansweredQuestionnaires);
    }

    public static History toHistory(HistoryDTO historyDTO) {
        List<Questionnaire> answeredQuestionnaires = new ArrayList<>();
        List<Questionnaire> unansweredQuestionnaires = new ArrayList<>();

        for (QuestionnaireDTO questionnaireDTO : historyDTO.getAnsweredQuestionnairesDTO())
            answeredQuestionnaires.add(QuestionnaireMapper.toQuestionnaire(questionnaireDTO));

        for (QuestionnaireDTO questionnaireDTO : historyDTO.getUnansweredQuestionnairesDTO())
            unansweredQuestionnaires.add(QuestionnaireMapper.toQuestionnaire(questionnaireDTO));

        return new History(new Identifier(historyDTO.getIdentifier()),
                answeredQuestionnaires,
                unansweredQuestionnaires);
    }
}
