package eapli.base.warehousemanagement.domain.Aisle;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class AisleId implements ValueObject, Comparable<AisleId> {

    @Column(name = "aisleId")
    private long aisleId;

    public AisleId() {
    }

    public AisleId(long aisleId) {
        setAisleId(aisleId);
    }

    private void setAisleId(long aisleId){
        if (aisleId < 0) {
            throw new IllegalArgumentException("Aisle id can't be a negative value.");
        }
        this.aisleId = aisleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AisleId aisleId1 = (AisleId) o;
        return aisleId == aisleId1.aisleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aisleId);
    }

    @Override
    public int compareTo(AisleId o) {
        return this.compareTo(o);
    }

    @Override
    public String toString() {
        return String.valueOf(aisleId);
    }
}
