package eapli.base.questionnairemanagement.dto;

import eapli.base.common.domain.model.Identifier;
import eapli.base.questionnairemanagement.domain.Message;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.base.questionnairemanagement.domain.Section;
import eapli.base.questionnairemanagement.domain.Title;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireMapper {
    public static QuestionnaireDTO toDTO(Questionnaire questionnaire) {
        String identifier = questionnaire.identifier().toString();
        String title =  questionnaire.title().getTitle();
        String introductionMessage = questionnaire.introductionMessage().getMessage();
        String finalMessage = questionnaire.finalMessage().getMessage();
        TargetAudienceDTO targetAudienceDTO = TargetAudienceMapper.toDTO(questionnaire.targetAudience());
        List<SectionDTO> sectionList = new ArrayList<>();

        for (Section section : questionnaire.sectionList())
            sectionList.add(SectionMapper.toDTO(section));

        return new QuestionnaireDTO(identifier, title, introductionMessage, finalMessage, sectionList, targetAudienceDTO);
    }

    public static QuestionnaireDTO toDTO(String identifier,
                                         String title,
                                         String introductionMessage,
                                         String finalMessage,
                                         List<SectionDTO> sectionList,
                                         TargetAudienceDTO targetAudienceDTO) {
        return new QuestionnaireDTO(identifier, title, introductionMessage, finalMessage, sectionList, targetAudienceDTO);
    }

    public static Questionnaire toQuestionnaire(QuestionnaireDTO questionnaireDTO) {
        List<Section> sectionList = new ArrayList<>();
        List<Message> messages = new ArrayList<>();

        for (SectionDTO sectionDTO : questionnaireDTO.getSectionList())
            sectionList.add(SectionMapper.toSection(sectionDTO));

        messages.add(new Message(questionnaireDTO.getIntroductionMessage()));
        messages.add(new Message(questionnaireDTO.getFinalMessage()));

        return new Questionnaire(new Identifier(questionnaireDTO.getIdentifier().replace("\n", "")),
                new Title(questionnaireDTO.getTitle()),
                messages,
                sectionList,
                TargetAudienceMapper.toTargetAudience(questionnaireDTO.getTargetAudience()));
    }
}
