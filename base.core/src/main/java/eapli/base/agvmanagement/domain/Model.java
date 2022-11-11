package eapli.base.agvmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Model implements ValueObject, Comparable<Model> {
    private static final String MODEL_REGEX = "[A-Za-z0-9 \\-]{0,9}";

    @Column(name = "Model")
    private String model;

    public Model(String model) throws IllegalArgumentException {
        if (!model.matches(MODEL_REGEX))
            throw new IllegalArgumentException("The Model \"" + model + "\" contains invalid characters or " +
                    "has an invalid format.");

        this.model = model;
    }

    protected Model() {
    }

    @Override
    public int compareTo(Model otherModel) {
        return model.compareTo(otherModel.model);
    }

    @Override
    public String toString() {
        return model;
    }

    public String getModel() {
        return model;
    }
}
