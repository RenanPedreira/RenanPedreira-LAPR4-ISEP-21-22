package eapli.base.common.domain.model;

import eapli.base.questionnairemanagement.domain.Message;
import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Comparator;

@Embeddable
public class Description implements ValueObject {
    public enum DescriptionType {
        SIMPLE_DESCRIPTION,
        SHORT_DESCRIPTION,
        EXTENDED_DESCRIPTION,
        TECHNICAL_DESCRIPTION;

        public static boolean contains(String value) {
            for (DescriptionType type : DescriptionType.values()) {
                if (type.name().equals(value))
                    return true;
            }

            return false;
        }
    }

    private static final int MAX_LENGTH_SHORT_DESCRIPTION = 60;
    private static final int MAX_LENGTH_EXTENDED_DESCRIPTION = 280;
    private static final int MAX_LENGTH_TECHNICAL_DESCRIPTION = 560;

    @Column(name = "Type")
    private String descriptionType;
    @Column(name = "Description")
    private String description;

    public Description(String descriptionType, String description) {
        descriptionType = descriptionType.toUpperCase();
        descriptionType = descriptionType.replace(" ", "_");

        if (!DescriptionType.contains(descriptionType))
            throw new IllegalArgumentException("The description type \"" + descriptionType + "\" isn't a valid " +
                    "description type.");
        else if (descriptionType.equals(DescriptionType.SHORT_DESCRIPTION)
                && description.length() > MAX_LENGTH_SHORT_DESCRIPTION)
            throw new IllegalArgumentException("The short description inserted surpasses the character limit of "
                    + MAX_LENGTH_SHORT_DESCRIPTION + " characters.");
        else if (descriptionType.equals(DescriptionType.EXTENDED_DESCRIPTION)
                && description.length() > MAX_LENGTH_EXTENDED_DESCRIPTION)
            throw new IllegalArgumentException("The extended description inserted surpasses the character limit of "
                    + MAX_LENGTH_EXTENDED_DESCRIPTION + " characters.");
        else if (descriptionType.equals(DescriptionType.TECHNICAL_DESCRIPTION)
                && description.length() > MAX_LENGTH_TECHNICAL_DESCRIPTION)
            throw new IllegalArgumentException("The extended description inserted surpasses the character limit of "
                    + MAX_LENGTH_TECHNICAL_DESCRIPTION + " characters.");

        this.descriptionType = descriptionType;
        this.description = description;
    }

    protected Description() {}

    @Override
    public String toString() {
        return description;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionType() {
        return descriptionType;
    }
}
