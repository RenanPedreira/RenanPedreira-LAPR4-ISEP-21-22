package eapli.base.common.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * Data Structure which represents the Domain Concept "ID" (c.f. Order_System.png and User_System.png)
 *
 * @author Gonçalo Monteiro
 */
@Embeddable
public class Identifier implements ValueObject, Comparable<Identifier> {
    private static final String ID_REGEX = "([A-Z0-9]+-[0-9]+|[0-9]+)";

    @Column(name = "id")
    private String identifier;

    public Identifier(String identifier) throws IllegalArgumentException {
        identifier = identifier.replace("\n", "");

        if (!identifier.matches(ID_REGEX) || StringPredicates.isNullOrWhiteSpace(identifier))
            throw new IllegalArgumentException("The ID \"" + identifier + "\" contains invalid characters or " +
                    "has an invalid format.");


        this.identifier = identifier;
    }

    protected Identifier() {}

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public int compareTo(Identifier otherIdentifier) {
        return identifier.compareTo(otherIdentifier.identifier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifier that = (Identifier) o;
        return Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(identifier).append("\n");

        return stringBuilder.toString();
    }

    public String toString2() {
        return this.identifier;
    }
}
