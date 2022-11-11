package eapli.base.warehousemanagement.domain.Row;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ShelveQuantity implements ValueObject {

    @Column(name = "shelveQuantity")
    private long shelveQuantity;

    public ShelveQuantity() {
    }

    public ShelveQuantity(long shelveQuantity) {
        setShelveQuantity(shelveQuantity);
    }

    private void setShelveQuantity(long shelveQuantity){
        if (shelveQuantity < 0) {
            throw new IllegalArgumentException("Shelve quantity can't be a negative value.");
        }
        this.shelveQuantity = shelveQuantity;
    }

    @Override
    public String toString() {
        return String.valueOf(shelveQuantity);
    }
}
