package eapli.base.statistics.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StatisticalData implements ValueObject, Comparable<StatisticalData> {
    private static final int MINIMAL_VALUE = 0;

    @Column(name = "Value")
    private double value;

    public StatisticalData(double value) {
        if (value < MINIMAL_VALUE)
            throw new IllegalArgumentException("The Value \"" + value + "\" isn't a valid Statistical Value.");

        this.value = value;
    }

    protected StatisticalData() {}

    @Override
    public int compareTo(StatisticalData otherStat) {
        return (int) (value - otherStat.getValue());
    }

    public double getValue() {
        return value;
    }
}
