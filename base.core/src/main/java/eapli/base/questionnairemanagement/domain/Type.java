package eapli.base.questionnairemanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

@Embeddable
public class Type implements ValueObject {
    public enum TypeEnumerate {
        FREE_TEXT,
        NUMERIC,
        SINGLE_CHOICE,
        SINGLE_CHOICE_WITH_INPUT,
        MULTIPLE_CHOICE,
        MULTIPLE_CHOICE_WITH_INPUT,
        SORTING_OPTION,
        SCALING_OPTION;

        public static boolean contains(String value) {
            for(TypeEnumerate type: TypeEnumerate.values()){
                if(type.name().equals(value))
                    return true;
            }
            return false;
        }
    }

    @Column(name = "Type")
    private String type;

    public Type(String type) {
        Preconditions.nonNull(type);

        String auxString = type.toUpperCase().replaceAll("[ -]", "_").replace(";", "");

        if(!TypeEnumerate.contains(auxString))
            throw new IllegalArgumentException("The Question Type\n" + type + "\" isn't a valid" +
                    "Question Type");

        this.type = type;
    }

    protected Type() {}

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(type);

        if (type.equals("Numeric") || type.equals("Free-Text"))
            stringBuilder.append(";\n");
        else
            stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    public String getType() {
        return type;
    }
}
