package eapli.base.customermanagement.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Data Structure which represents the Domain Concept "Gender" (c.f. Order_System.png)
 *
 * @author Gonçalo Monteiro
 */
@Embeddable
public class Gender implements ValueObject, Comparable<Gender> {
    private enum Genders {
        MALE,
        FEMALE,
        NONBINARY;

        public static boolean contains(String value) {
            for (Genders gender : Genders.values()) {
                if (gender.name().equals(value))
                    return true;
            }

            return false;
        }
    }

    @Column(name = "Gender")
    private String gender;

    public Gender(String gender) throws IllegalArgumentException {
        Preconditions.nonNull(gender);

        if (!Genders.contains(gender.toUpperCase().replaceAll("-", "")))
            throw new IllegalArgumentException("The gender \"" + gender + "\" isn't a valid gender.");

        this.gender = gender;
    }

    protected Gender() {}

    public String getGender() {
        return gender;
    }

    @Override
    public int compareTo(Gender otherGender) {
        return gender.compareTo(otherGender.gender);
    }

    @Override
    public String toString() {
        return gender;
    }
}
