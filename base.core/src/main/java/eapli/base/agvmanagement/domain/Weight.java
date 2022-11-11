package eapli.base.agvmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Weight implements ValueObject, Comparable<Weight> {
    private static final double MINIMAL_WEIGHT = 0;
    private static final double MAXIMAL_WEIGHT = 1000;

    @Column(name = "Weight")
    private double weight;

    public Weight(double weight) {
        if (weight < MINIMAL_WEIGHT || weight > MAXIMAL_WEIGHT)
            throw new IllegalArgumentException("The weight \"" + weight + "\" isn't a valid weight.");

        this.weight = weight;
    }

    protected Weight() {}

    @Override
    public int compareTo(Weight otherWeight) {
        return (int) (weight - otherWeight.weight);
    }

    @Override
    public String toString() {
        return String.valueOf(weight);
    }

    public double getWeight() {
        return weight;
    }
}
