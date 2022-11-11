package eapli.base.warehousemanagement.domain.AgvDock;

import eapli.base.warehousemanagement.domain.Aisle.Accessibility;
import eapli.base.warehousemanagement.domain.Aisle.Square;
import eapli.base.warehousemanagement.domain.Warehouse.Warehouse;
import eapli.base.warehousemanagement.dto.DockDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.dto.DTOable;

import javax.persistence.*;

@Entity
public class AgvDock implements AggregateRoot<AgvDockId>, DTOable<DockDTO> {

    @EmbeddedId
    private AgvDockId agvDockId;

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

    public AgvDock() {
    }

    public AgvDock(AgvDockId agvDockId, Square begin, Square end, Square depth, Accessibility accessibility, Warehouse warehouse) {
        this.agvDockId = agvDockId;
        this.begin = begin;
        this.end = end;
        this.depth = depth;
        this.accessibility = accessibility;
        this.warehouse = warehouse;
    }

    @Override
    public boolean sameAs(Object other) {
        AgvDock agvDock = (AgvDock) other;
        return this.agvDockId.equals(agvDock.agvDockId);
    }

    @Override
    public AgvDockId identity() {
        return this.agvDockId;
    }

    @Override
    public String toString() {
        return "Agv dock id: " + agvDockId +
                ", begin square: " + begin +
                ", end square: " + end +
                ", depth square: " + depth +
                ", accessibility: " + accessibility;
    }

    @Override
    public DockDTO toDTO() {
        return new DockDTO(agvDockId.toString());
    }
}
