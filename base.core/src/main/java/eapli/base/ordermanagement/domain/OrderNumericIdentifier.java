package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 *
 * @author Renan Pedreira
 */
@Embeddable
public class OrderNumericIdentifier implements ValueObject, Comparable<OrderNumericIdentifier> {
    @Column(name = "OrderNumericIdentifier")
    private Integer numericIdentifier;

    public OrderNumericIdentifier(){}

    public OrderNumericIdentifier(final Integer numericIdentifier){
        this.numericIdentifier=numericIdentifier;
    }

    public int getNumericIdentifier() {
        return numericIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderNumericIdentifier that = (OrderNumericIdentifier) o;
        return Objects.equals(numericIdentifier, that.numericIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numericIdentifier);
    }

    @Override
    public String toString() {
        return String.valueOf(this.numericIdentifier);
    }

    @Override
    public int compareTo(OrderNumericIdentifier o) {
        return this.numericIdentifier.compareTo(Integer.parseInt(o.toString()));
    }
}
