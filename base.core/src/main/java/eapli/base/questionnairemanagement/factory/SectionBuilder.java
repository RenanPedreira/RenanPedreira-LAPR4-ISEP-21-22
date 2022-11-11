package eapli.base.questionnairemanagement.factory;

import eapli.base.common.domain.model.Description;
import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.common.util.Pair;
import eapli.base.questionnairemanagement.domain.*;
import eapli.base.questionnairemanagement.domain.Number;

import java.util.List;

public class SectionBuilder {
    private Identifier identifier;
    private Number number;
    private Title title;
    private Description description;
    private Obligatoriness obligatoriness;
    private Repeatability repeatability;
    private List<Question> content;

    public void setIdentifier(String identifier) {
        this.identifier = new Identifier(identifier);
    }

    public void setIdentifier() {
        this.identifier = new IdentifierGenerator().generateIdentifier();
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public void setTitle(String title) {
        this.title = new Title(title);
    }

    public void setDescription(String description) {
        this.description = new Description(Description.DescriptionType.SIMPLE_DESCRIPTION.name(), description);
    }

    public void setObligatoriness(Pair<String, String> obligatoriness) {
        this.obligatoriness = new Obligatoriness(obligatoriness.getKey(), obligatoriness.getValue());
    }

    public void setRepeatability(Pair<String, String> repeatability) {
        this.repeatability = new Repeatability(repeatability.getKey(), repeatability.getValue());
    }

    public void setContent(List<Question> content) {
        this.content = content;
    }

    public Section build() {
        return new Section(identifier, number, title, description, obligatoriness, repeatability, content);
    }
}
