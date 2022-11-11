package eapli.base.questionnairemanagement.dto;

import java.util.List;

public class QuestionnaireDTO {
    private String identifier;
    private String title;
    private String introductionMessage;
    private String finalMessage;
    private List<SectionDTO> sectionList;
    private TargetAudienceDTO targetAudience;

    public QuestionnaireDTO(String identifier, String title, String introductionMessage, String finalMessage, List<SectionDTO> sectionList, TargetAudienceDTO targetAudience) {
        this.identifier = identifier;
        this.title = title;
        this.introductionMessage = introductionMessage;
        this.finalMessage = finalMessage;
        this.sectionList = sectionList;
        this.targetAudience = targetAudience;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\tQuestionnaire")
                .append("\nIdentifier: ").append(identifier)
                .append("\nTitle: ").append(title)
                .append("\nIntroduction Message: ").append(introductionMessage);

        for (SectionDTO sectionDTO : sectionList)
            stringBuilder.append(sectionDTO);

        stringBuilder.append("\nEnd Message: ").append(finalMessage);

        return stringBuilder.toString();
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getTitle() {
        return title;
    }

    public String getIntroductionMessage() {
        return introductionMessage;
    }

    public String getFinalMessage() {
        return finalMessage;
    }

    public List<SectionDTO> getSectionList() {
        return sectionList;
    }

    public TargetAudienceDTO getTargetAudience() {
        return targetAudience;
    }
}
