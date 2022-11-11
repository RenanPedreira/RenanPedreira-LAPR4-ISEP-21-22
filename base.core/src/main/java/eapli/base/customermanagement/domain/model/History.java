package eapli.base.customermanagement.domain.model;

import eapli.base.common.domain.model.Identifier;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.DomainEntity;

import javax.persistence.*;
import java.util.List;

@Entity
public class History implements DomainEntity<Identifier> {
    @EmbeddedId
    private Identifier identifier;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "AnsweredQuestionnaires")
    private List<Questionnaire> answeredQuestionnaires;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "UnansweredQuestionnaires")
    private List<Questionnaire> unansweredQuestionnaires;

    public History(Identifier identifier, List<Questionnaire> answeredQuestionnaires, List<Questionnaire> unansweredQuestionnaires) {
        this.identifier = identifier;
        this.answeredQuestionnaires = answeredQuestionnaires;
        this.unansweredQuestionnaires = unansweredQuestionnaires;
    }

    protected History() {}

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Identifier identity() {
        return identifier;
    }
    public Identifier getIdentifier() {
        return identity();
    }

    public List<Questionnaire> answeredQuestionnaires() {
        return answeredQuestionnaires;
    }

    public List<Questionnaire> unansweredQuestionnaires() {
        return unansweredQuestionnaires;
    }
}
