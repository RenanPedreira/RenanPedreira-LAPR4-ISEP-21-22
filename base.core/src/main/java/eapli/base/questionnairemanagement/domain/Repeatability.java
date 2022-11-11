package eapli.base.questionnairemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Comparator;

@Embeddable
public class Repeatability implements ValueObject, Comparable<Repeatability>  {
    public enum RepeatabilityType {
        BASED_ON_NUMERIC_ANSWER,
        BASED_ON_SELECTED_VALUES;

        public static boolean contains(String value) {
            for(RepeatabilityType type: RepeatabilityType.values()){
                if(type.name().equals(value))
                    return true;
            }

            return false;
        }
    }

    @Column(name = "RepeatabilityType")
    private String type;

    @Column(name = "RepeatabilityCondition")
    private String condition;

    public Repeatability(String type, String condition) {
        Preconditions.nonNull(condition);

        String auxString = type.toUpperCase().replace(" ", "_");

        if (!RepeatabilityType.contains(auxString))
            throw new IllegalArgumentException("The Repeatability Type\n" + type + "\" isn't a valid" +
                    "Repeatability Type");

        this.type = type;
        this.condition = condition;
    }

    protected Repeatability() {}

    @Override
    public int compareTo(Repeatability otherRepeatability) {
        return Comparator.comparing(Repeatability::getType)
                .thenComparing(Repeatability::getCondition)
                .compare(this, otherRepeatability);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(type).append(" (").append(condition).append(");\n");

        return stringBuilder.toString();
    }

    public String getType() {
        return type;
    }

    public String getCondition() {
        return condition;
    }
}
