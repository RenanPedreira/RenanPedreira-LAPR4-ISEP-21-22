package eapli.base.questionnairemanagement.dto;

import eapli.base.common.util.Pair;

import java.util.List;
import java.util.Set;

public class SectionDTO {
    private String identifier;
    private Integer number;
    private String title;
    private String description;
    private Pair<String, String> obligatoriness;
    private Pair<String, String> repeatability;
    private List<QuestionDTO> content;

    public SectionDTO(String identifier,
                      Integer number,
                      String title,
                      String description,
                      Pair<String, String> obligatoriness,
                      Pair<String, String> repeatability,
                      List<QuestionDTO> content) {
        this.identifier = identifier;
        this.number = number;
        this.title = title;
        this.description = description;
        this.obligatoriness = obligatoriness;
        this.repeatability = repeatability;
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("\tSection")
                .append("\nIdentifier: ").append(identifier)
                .append("\nTitle: ").append(title)
                .append("\nDescription: ").append(description)
                .append("\nObligatoriness: ").append(obligatoriness.getKey());

        if (obligatoriness.getValue() != null)
            stringBuilder.append(" [").append(obligatoriness.getValue()).append("]");

        for (QuestionDTO questionDTO : content)
            stringBuilder.append(questionDTO);

        return stringBuilder.toString();
    }

    public String getIdentifier() {
        return identifier;
    }

    public Integer getNumber() { return number; }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Pair<String, String> getObligatoriness() {
        return obligatoriness;
    }

    public Pair<String, String> getRepeatability() {
        return repeatability;
    }

    public List<QuestionDTO> getContent() {
        return content;
    }
}
