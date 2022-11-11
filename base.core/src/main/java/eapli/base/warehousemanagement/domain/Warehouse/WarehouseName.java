package eapli.base.warehousemanagement.domain.Warehouse;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class WarehouseName implements ValueObject, Comparable<WarehouseName> {

    @Column(name = "warehouseName")
    private String warehouseName;

    public WarehouseName() {
    }

    public WarehouseName(String warehouseName) {
        setWarehouseName(warehouseName);
    }

    private void setWarehouseName(String warehouseName){
        if (StringPredicates.isNullOrWhiteSpace(warehouseName)) {
            throw new IllegalArgumentException("Warehouse name cannot be empty.");
        }
        this.warehouseName = warehouseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseName that = (WarehouseName) o;
        return Objects.equals(warehouseName, that.warehouseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseName);
    }

    @Override
    public String toString() {
        return warehouseName;
    }

    @Override
    public int compareTo(WarehouseName o) {
        return this.compareTo(o);
    }
}
