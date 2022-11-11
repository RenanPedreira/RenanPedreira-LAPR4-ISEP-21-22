package eapli.base.productmanagement.domain.Categorya;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Alpha implements ValueObject, Comparable<Alpha> {

    @Column(name = "alpha")
    private String alpha;

    public Alpha(){

    }

    public Alpha(final String alpha) {
        if(alpha.length()> 50 || alpha == null) {
            throw new IllegalArgumentException("Alpha needs to have 50 char max and not empty");
        }
        this.alpha = alpha;
    }

    public String getAlpha() { return alpha;}

    @Override
    public String toString() { return alpha; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alpha alpha1 = (Alpha) o;
        return Objects.equals(alpha, alpha1.alpha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alpha);
    }

    @Override
    public int compareTo(Alpha o) {
        return this.alpha.compareTo(o.toString());
    }
}
