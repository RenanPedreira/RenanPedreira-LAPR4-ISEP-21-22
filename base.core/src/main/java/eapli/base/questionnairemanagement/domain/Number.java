package eapli.base.questionnairemanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Number implements ValueObject, Comparable<Number> {
    @Column(name = "Number")
    private Integer number;

    public Number(Integer number) {
        this.number = number;
    }

    protected Number() {}


    @Override
    public int compareTo(Number number) {
        return this.number.compareTo(number.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return Objects.equals(number, number1.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    public Integer getNumber() {
        return number;
    }
}
