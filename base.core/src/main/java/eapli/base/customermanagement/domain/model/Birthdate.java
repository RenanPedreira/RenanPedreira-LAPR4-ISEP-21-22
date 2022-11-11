package eapli.base.customermanagement.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Data Structure which represents the Domain Concept "Birthdate" (c.f. User_Management_DM.png)
 *
 * @author Gonçalo Monteiro
 */
@Embeddable
public class Birthdate implements ValueObject, Comparable<Birthdate> {
    private static final long MAXIMUM_AGE = 100;

    @Column(name = "Birthdate")
    private LocalDate birthdate;

    public Birthdate(LocalDate birthdate) {
        Preconditions.nonNull(birthdate);

        if (ChronoUnit.YEARS.between(birthdate, LocalDate.now()) > MAXIMUM_AGE)
            throw new IllegalArgumentException("The birthdate \"" + birthdate + "\" isn't a valid date.");

        this.birthdate = birthdate;
    }

    protected Birthdate() {}

    @Override
    public int compareTo(Birthdate otherDate) {
        return birthdate.compareTo(otherDate.birthdate);
    }

    @Override
    public String toString() {
        return birthdate.toString();
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
}
