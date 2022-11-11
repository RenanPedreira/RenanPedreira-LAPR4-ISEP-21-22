package eapli.base.statistics.domain;

import eapli.base.common.domain.model.Identifier;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.util.List;

@Entity
public class StatisticalReport implements AggregateRoot<Identifier> {
    @Version
    private long version;

    @EmbeddedId
    private Identifier identifier;
    @OneToOne(cascade = CascadeType.ALL)
    private Questionnaire questionnaire;
    @ElementCollection
    @AttributeOverride(name = "Value", column = @Column(name = "RawData"))
    private List<StatisticalData> rawDataList;
    @ElementCollection
    @AttributeOverride(name = "Value", column = @Column(name = "Percentage"))
    private List<StatisticalData> percentageList;

    public StatisticalReport(Identifier identifier, Questionnaire questionnaire, List<StatisticalData> rawDataList, List<StatisticalData> percentageList) {
        this.identifier = identifier;
        this.questionnaire = questionnaire;
        this.rawDataList = rawDataList;
        this.percentageList = percentageList;
    }

    protected StatisticalReport() {}

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

    public Questionnaire questionnaire() {
        return questionnaire;
    }

    public List<StatisticalData> rawDataList() {
        return rawDataList;
    }

    public List<StatisticalData> percentageList() {
        return percentageList;
    }
}
