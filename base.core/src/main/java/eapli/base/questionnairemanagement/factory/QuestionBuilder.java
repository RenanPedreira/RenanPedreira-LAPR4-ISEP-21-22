package eapli.base.questionnairemanagement.factory;

import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.common.util.Pair;
import eapli.base.questionnairemanagement.domain.*;
import eapli.base.questionnairemanagement.domain.Number;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class QuestionBuilder {
    private Identifier identifier;
    private Number number;
    private Interrogation interrogation;
    private Instruction instruction;
    private Type type;
    private List<Message> optionList;
    private Obligatoriness obligatoriness;
    private Message extraInformation;
    private LinkedList<Answer> answers;

    public void setIdentifier(String identifier) {
        this.identifier = new Identifier(identifier);
    }

    public void setIdentifier() {
        this.identifier = new IdentifierGenerator().generateIdentifier();
    }

    public void setNumber(Integer number) {
        this.number = new Number(number);
    }

    public void setInterrogation(String interrogation) {
        this.interrogation = new Interrogation(interrogation);
    }

    public void setInstruction(String instruction) {
        this.instruction = new Instruction(instruction);
    }

    public void setType(String type) {
        this.type = new Type(type);
    }

    public void setOptionList(List<String> optionList) {
        List<Message> auxiliaryArray = new ArrayList<>();

        for (String option : optionList)
            auxiliaryArray.add(new Message(option));

        this.optionList = auxiliaryArray;
    }

    public void setObligatoriness(Pair<String, String> obligatoriness) {
        this.obligatoriness = new Obligatoriness(obligatoriness.getKey(), obligatoriness.getValue());
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = new Message(extraInformation);
    }

    public void setAnswers(List<String> answers) {
        LinkedList<Answer> answerList = new LinkedList<>();

        for (String answer : answers)
            answerList.add(new Answer(answer));

        this.answers = answerList;
    }

    public Question build() {
        return new Question(identifier, number, interrogation, instruction, type, optionList, obligatoriness, extraInformation, answers);
    }
}
