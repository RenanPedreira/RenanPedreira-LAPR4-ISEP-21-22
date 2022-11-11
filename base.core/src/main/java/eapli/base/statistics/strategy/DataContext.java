package eapli.base.statistics.strategy;

import eapli.base.statistics.domain.StatisticalData;

import java.util.List;

public class DataContext {
    private DataStrategy dataStrategy;

    public void setDataStrategy(DataStrategy dataStrategy) {
        this.dataStrategy = dataStrategy;
    }

    public List<StatisticalData> executeStrategy(Parameter parameter) {
        return dataStrategy.execute(parameter);
    }
}
