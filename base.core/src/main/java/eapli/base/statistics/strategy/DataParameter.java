package eapli.base.statistics.strategy;

import eapli.base.questionnairemanagement.domain.Question;
import eapli.base.statistics.domain.StatisticalData;

import java.util.List;

public class DataParameter extends Parameter {
    private List<StatisticalData> dataListParameter;

    public DataParameter(Question questionParameter, List<StatisticalData> dataListParameter) {
        super(questionParameter);

        this.dataListParameter = dataListParameter;
    }

    public List<StatisticalData> getDataParameter() {
        return dataListParameter;
    }
}
