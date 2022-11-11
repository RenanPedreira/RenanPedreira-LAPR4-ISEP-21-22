package eapli.base.questionnairemanagement.domain;

import eapli.base.common.domain.model.Identifier;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
public class Question implements DomainEntity<Identifier> {
    private static final String NUMBER_DELIMITER1 = "{";
    private static final String NUMBER_DELIMITER2 = "}";
    private static final int MIN_SIZE = 1;

    @EmbeddedId
    private Identifier identifier;
    @Embedded
    private Number number;
    @Embedded
    private Interrogation interrogation;
    @Embedded
    private Instruction instruction;
    @Embedded
    private Type type;
    @ElementCollection
    private List<Message> optionList;
    @Embedded
    private Obligatoriness obligatoriness;
    @Embedded
    private Message extraInformation;
    @ElementCollection
    private List<Answer> answers;

    public Question(Identifier identifier,
                    Number number,
                    Interrogation interrogation,
                    Instruction instruction,
                    Type type,
                    List<Message> optionList,
                    Obligatoriness obligatoriness,
                    Message extraInformation,
                    LinkedList<Answer> answers) {
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

    protected Question() {}

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(NUMBER_DELIMITER1).append(number).append(NUMBER_DELIMITER2).append(" ")
                .append(interrogation)
                .append("Instructions: ").append(instruction)
                .append("Obligatoriness: ").append(obligatoriness)
                .append("Type: ").append(type).append("\n");

        if (!optionList.isEmpty()) {
            int count = 1;

            stringBuilder.append("Option List:\n");

            for (Message option : optionList)
                stringBuilder.append(count++).append(": ").append(option);

            stringBuilder.append("\n");
        }

        if (extraInformation.getMessage() != null)
            stringBuilder.append("Extra Information: ").append(extraInformation);

        stringBuilder.append("Answer: ")
                .append(answers.get(0))
                .append(";\n\n");

        return stringBuilder.toString();
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Identifier identity() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    public Identifier identifier() {
        return identifier;
    }

    public Number number() { return number; }

    public Instruction instruction() {
        return instruction;
    }

    public Interrogation interrogation() { return interrogation; }

    public Type getType() {
        return type;
    }

    public List<Message> getOptionList() {
        return optionList;
    }

    public Obligatoriness obligatoriness() {
        return obligatoriness;
    }

    public Message extraInformation() {
        return extraInformation;
    }

    public List<Answer> answers() {
        return answers;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(0, answer);
    }

    public void clearAnswers() {
        this.answers = new LinkedList<>();
    }
}
