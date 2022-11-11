package eapli.base.statistics.strategy;

import eapli.base.statistics.domain.StatisticalData;

import java.util.ArrayList;
import java.util.List;

public class PercentageDataStrategy implements DataStrategy {
    @Override
    public List<StatisticalData> execute(Parameter parameter) {
        List<StatisticalData> percentages = new ArrayList<>();

        double total = 0;

        for (StatisticalData statisticalData : ((DataParameter) parameter).getDataParameter())
            total += statisticalData.getValue();

        for (StatisticalData value : ((DataParameter) parameter).getDataParameter())
            percentages.add(new StatisticalData((value.getValue() / total) * 100));

        return percentages;
    }
}
