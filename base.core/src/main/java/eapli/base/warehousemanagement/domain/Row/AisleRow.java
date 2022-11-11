package eapli.base.warehousemanagement.domain.Row;

import eapli.base.warehousemanagement.domain.Aisle.Aisle;
import eapli.base.warehousemanagement.domain.Aisle.Square;
import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;

@Entity
public class AisleRow implements AggregateRoot<RowId> {

    @EmbeddedId
    private RowId rowId;

    @AttributeOverrides({
            @AttributeOverride(name="lsquare",column=@Column(name="beginlsquare")),
            @AttributeOverride(name="wsquare",column=@Column(name="beginwsquare")),
    })
    @Embedded
    private Square begin;

    @AttributeOverrides({
            @AttributeOverride(name="lsquare",column=@Column(name="endlsquare")),
            @AttributeOverride(name="wsquare",column=@Column(name="endwsquare")),
    })
    @Embedded
    private Square end;

    @Embedded
    private ShelveQuantity shelveQuantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "aisle")
    private Aisle aisle;

    public AisleRow() {
    }

    public AisleRow(RowId rowId, Square begin, Square end, ShelveQuantity shelveQuantity, Aisle aisle) {
        this.rowId = rowId;
        this.begin = begin;
        this.end = end;
        this.shelveQuantity = shelveQuantity;
        this.aisle = aisle;
    }

    @Override
    public boolean sameAs(Object other) {
        AisleRow aisleRow = (AisleRow)other;
        return this.rowId.equals(aisleRow.rowId);
    }

    @Override
    public RowId identity() {
        return this.rowId;
    }

    @Override
    public String toString() {
        return "Row id: " + rowId +
                ", begin square: " + begin +
                ", end square: " + end +
                ", shelves quantity: " + shelveQuantity +
                ", aisle id: " + aisle.identity();
    }

    public RowId getRowId(){
        return rowId;
    }

    public Square getBegin() {
        return begin;
    }

    public Square getEnd() {
        return end;
    }

    public ShelveQuantity getShelveQuantity() {
        return shelveQuantity;
    }

    public Aisle getAisleFromRow() {
        return aisle;
    }
}
