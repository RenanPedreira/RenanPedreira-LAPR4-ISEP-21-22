package eapli.base.questionnairemanagement.domain;

import eapli.base.common.domain.model.Identifier;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Questionnaire implements AggregateRoot<Identifier> {
    private static final int INTRODUCTION_MESSAGE_INDEX = 0;
    private static final int FINAL_MESSAGE_INDEX = 1;
    private static final String END_TITLE = "END OF SURVEY";

    @EmbeddedId
    private Identifier identifier;

    @Embedded
    private Title title;

    @ElementCollection
    private List<Message> messages;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Section> sectionList;

    @OneToOne(cascade = CascadeType.ALL)
    private TargetAudience targetAudience;

    public Questionnaire(Identifier identifier,
                         Title title,
                         List<Message> messages,
                         List<Section> sectionList,
                         TargetAudience targetAudience) {
        this.identifier = identifier;
        this.title = title;
        this.messages = messages;
        this.sectionList = sectionList;
        this.targetAudience = targetAudience;
    }

    protected Questionnaire() {}

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(identifier).append("\n")
                .append(title).append("\n")
                .append(introductionMessage()).append("\n");

        for (Section section : sectionList)
            stringBuilder.append(section);

        stringBuilder.append(END_TITLE).append("\n").append(finalMessage());

        return stringBuilder.toString();
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Identifier identity() {
        return this.identifier;
    }

    public Identifier identifier() {
        return identity();
    }

    public Title title() {
        return title;
    }

    public Message introductionMessage() {
        return messages.get(INTRODUCTION_MESSAGE_INDEX);
    }

    public Message finalMessage() {
        return messages.get(FINAL_MESSAGE_INDEX);
    }

    public List<Section> sectionList() {
        return sectionList;
    }

    public TargetAudience targetAudience() {
        return targetAudience;
    }
}
