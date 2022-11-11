package eapli.base.questionnairemanagement.dto;

import eapli.base.common.util.Pair;

import java.util.List;

public class QuestionDTO {
    private String identifier;
    private Integer number;
    private String interrogation;
    private String instruction;
    private String type;
    private List<String> optionList;
    private Pair<String, String> obligatoriness;
    private String extraInformation;
    private List<String> answers;

    public QuestionDTO(String identifier,
                       Integer number,
                       String interrogation,
                       String instruction,
                       String type,
                       List<String> optionList,
                       Pair<String, String> obligatoriness,
                       String extraInformation,
                       List<String> answers) {
        this.identifier = identifier;
        this.number = number;
        this.interrogation = interrogation;
        this.instruction = instruction;
        this.type = type;
        this.optionList = optionList;
        this.obligatoriness = obligatoriness;
        this.extraInformation = extraInformation;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return null;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Integer getNumber() { return number; }

    public String getInstruction() {
        return instruction;
    }

    public String getInterrogation() {
        return interrogation;
    }

    public String getType() {
        return type;
    }

    public List<String> getOptionList() {
        return optionList;
    }

    public Pair<String, String> getObligatoriness() {
        return obligatoriness;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public List<String> getAnswers() { return answers; }
}
