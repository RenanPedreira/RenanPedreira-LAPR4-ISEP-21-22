package eapli.base.warehousemanagement.domain.Row;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class RowId implements ValueObject, Comparable<RowId> {

    @Column(name = "rowId")
    private long rowId;

    public RowId() {
    }

    public RowId(long rowId) {
        setRowId(rowId);
    }

    public void setRowId(long rowId) {
        if (rowId < 0){
            throw new IllegalArgumentException("Row id can't be a negative value.");
        }
        this.rowId = rowId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RowId rowId1 = (RowId) o;
        return rowId == rowId1.rowId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowId);
    }

    @Override
    public int compareTo(RowId o) {
        return this.compareTo(o);
    }

    @Override
    public String toString() {
        return String.valueOf(rowId);
    }
}
