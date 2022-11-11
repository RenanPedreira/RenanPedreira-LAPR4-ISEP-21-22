package eapli.base.questionnairemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Interrogation implements ValueObject {
    private static final String INTERROGATION_REGEX = ".*\\?$";

    @Column(name = "Interrogation")
    private String interrogation;

    public Interrogation(String interrogation) {
        Preconditions.nonNull(interrogation);

        if (!interrogation.matches(INTERROGATION_REGEX))
            throw new IllegalArgumentException("The sentence inserted has an invalid format.");

        this.interrogation = interrogation;
    }

    protected Interrogation() {}

    @Override
    public String toString() {
        return interrogation + "\n";
    }

    public String getInterrogation() {
        return interrogation;
    }
}
