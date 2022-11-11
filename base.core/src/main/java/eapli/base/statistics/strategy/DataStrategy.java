package eapli.base.statistics.strategy;

import eapli.base.questionnairemanagement.domain.Question;
import eapli.base.statistics.domain.StatisticalData;

import java.util.List;

public interface DataStrategy {
    List<StatisticalData> execute(Parameter parameter);
}
