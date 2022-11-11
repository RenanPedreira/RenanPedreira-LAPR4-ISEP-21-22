package eapli.base.warehousemanagement.domain.Aisle;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Accessibility implements ValueObject {

    @Column(name = "accessibility")
    private String accessibility;

    public Accessibility(String accessibility) {
        setAccessibility(accessibility);
    }

    public Accessibility() {
    }

    public String getAccessibility() {
        return accessibility;
    }

    private void setAccessibility(String accessibility) {
        if (StringPredicates.isNullOrWhiteSpace(accessibility)) {
            throw new IllegalArgumentException("Accessibility dock id cannot be empty.");
        }
        this.accessibility = accessibility;
    }

    @Override
    public String toString() {
        return accessibility;
    }
}
