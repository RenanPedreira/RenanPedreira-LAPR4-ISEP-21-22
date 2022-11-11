package eapli.base.warehousemanagement.domain.Aisle;

import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import eapli.framework.domain.model.AggregateRoot;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class Aisle implements AggregateRoot<AisleId> {

    @EmbeddedId
    private AisleId aisleId;

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

    @AttributeOverrides({
            @AttributeOverride(name="lsquare",column=@Column(name="depthlsquare")),
            @AttributeOverride(name="wsquare",column=@Column(name="depthwsquare")),
    })
    @Embedded
    private Square depth;

    @Embedded
    private Accessibility accessibility;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse")
    private Warehouse warehouse;

    public Aisle() {
    }

    public Aisle(AisleId aisleId, Square begin, Square end, Square depth, Accessibility accessibility, Warehouse warehouse) {
        this.aisleId = aisleId;
        this.begin = begin;
        this.end = end;
        this.depth = depth;
        this.accessibility = accessibility;
        this.warehouse = warehouse;
    }

    @Override
    public boolean sameAs(Object other) {
        Aisle aisle = (Aisle) other;
        return this.aisleId.equals(aisle.aisleId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aisle aisle = (Aisle) o;
        return Objects.equals(aisleId, aisle.aisleId) && Objects.equals(begin, aisle.begin) && Objects.equals(end, aisle.end) && Objects.equals(depth, aisle.depth) && Objects.equals(accessibility, aisle.accessibility) && Objects.equals(warehouse, aisle.warehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aisleId, begin, end, depth, accessibility, warehouse);
    }

    @Override
    public AisleId identity() {
        return this.aisleId;
    }

    @Override
    public String toString() {
        return "Aisle id: " + aisleId +
                ", begin square: " + begin +
                ", end square: " + end +
                ", depth square: " + depth +
                ", accessibility: " + accessibility;
    }
}
