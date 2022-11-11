package eapli.base.statistics.factory;

import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.questionnairemanagement.domain.Questionnaire;
import eapli.base.statistics.domain.StatisticalData;
import eapli.base.statistics.domain.StatisticalReport;

import java.util.List;

public class StatisticalReportBuilder {
    private Identifier identifier;
    private Questionnaire questionnaire;
    private List<StatisticalData> rawDataList;
    private List<StatisticalData> percentageList;

    public void setIdentifier(String identifier) {
        this.identifier = new Identifier(identifier);
    }

    public void setIdentifier() {
        this.identifier = new IdentifierGenerator().generateIdentifier();
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public void setRawDataList(List<StatisticalData> rawDataList) {
        this.rawDataList = rawDataList;
    }

    public void setPercentageList(List<StatisticalData> percentageList) {
        this.percentageList = percentageList;
    }

    public StatisticalReport build() {
        return new StatisticalReport(identifier, questionnaire, rawDataList, percentageList);
    }
}
