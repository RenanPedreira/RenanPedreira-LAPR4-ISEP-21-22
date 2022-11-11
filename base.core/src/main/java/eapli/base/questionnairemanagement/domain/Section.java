package eapli.base.questionnairemanagement.domain;

import eapli.base.common.domain.model.Description;
import eapli.base.common.domain.model.Identifier;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Section implements DomainEntity<Identifier> {
    @EmbeddedId
    private Identifier identifier;
    @Embedded
    private Number number;
    @Embedded
    private Title title;
    @Embedded
    private Description description;
    @Embedded
    private Obligatoriness obligatoriness;
    @Embedded
    private Repeatability repeatability;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> content;

    public Section(Identifier identifier,
                   Number number,
                   Title title,
                   Description description,
                   Obligatoriness obligatoriness,
                   Repeatability repeatability,
                   List<Question> content) {
        this.identifier = identifier;
        this.number = number;
        this.title = title;
        this.description = description;
        this.obligatoriness = obligatoriness;
        this.repeatability = repeatability;
        this.content = content;
    }

    protected Section() {}

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(number).append("-")
                .append(title).append("\n")
                .append(description)
                .append("Obligatoriness: ").append(obligatoriness);

        if (repeatability != null)
            stringBuilder.append("Repeatability: ").append(repeatability);

        stringBuilder.append("\n");

        for (Question question : content)
            stringBuilder.append(question);

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Identifier identity() {
        return identifier;
    }

    public Identifier identifier() {
        return identity();
    }

    public Number number() { return number; }

    public Title title() {
        return title;
    }

    public Description description() {
        return description;
    }

    public Obligatoriness obligatoriness() {
        return obligatoriness;
    }

    public Repeatability repeatability() {
        return repeatability;
    }

    public List<Question> content() {
        return content;
    }
}
