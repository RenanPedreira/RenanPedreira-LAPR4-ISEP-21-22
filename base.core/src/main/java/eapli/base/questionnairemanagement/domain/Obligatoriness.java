package eapli.base.questionnairemanagement.domain;


import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Comparator;

@Embeddable
public class Obligatoriness implements ValueObject, Comparable<Obligatoriness> {
    private static final String NULL_STRING = "null";

    public enum ObligatorinessType {
        MANDATORY,
        OPTIONAL,
        CONDITION_DEPENDENT;

        public static boolean contains(String value) {
            for(ObligatorinessType type: ObligatorinessType.values()){
                if(type.name().equals(value))
                    return true;
            }
            return false;
        }
    }

    @Column(name = "ObligatorinessType")
    private String type;

    @Column(name = "ObligatorinessCondition")
    private String condition;

    public Obligatoriness(String type, String condition) {
        Preconditions.nonNull(type);

        String auxString = type.toUpperCase().replace(" ", "_");

        if(!ObligatorinessType.contains(auxString))
            throw new IllegalArgumentException("The Obligatoriness Type\n" + type + "\" isn't a valid" +
                    "Obligatoriness Type");
        if (type.equals(ObligatorinessType.CONDITION_DEPENDENT))
            Preconditions.nonNull(condition);

        this.type = type;
        this.condition = condition;
    }

    protected Obligatoriness(){}

    @Override
    public int compareTo(Obligatoriness otherObligatoriness) {
        return Comparator.comparing(Obligatoriness::getType)
                .thenComparing(Obligatoriness::getCondition)
                .compare(this, otherObligatoriness);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(type);

        if (!condition.equals(NULL_STRING))
            stringBuilder.append(" (").append(condition).append(")");

        stringBuilder.append(";\n");

        return stringBuilder.toString();
    }

    public String getType() {
        return type;
    }

    public String getCondition() {
        return condition;
    }
}

