package eapli.base.warehousemanagement.domain.AgvDock;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class AgvDockId implements ValueObject, Comparable<AgvDockId> {

    @Column(name = "avgDockId")
    private String avgDockId;

    public AgvDockId() {
    }

    public AgvDockId(String avgDockId) {
        setAgvDockId(avgDockId);
    }

    private void setAgvDockId(String avgDockId){
        if (StringPredicates.isNullOrWhiteSpace(avgDockId)) {
            throw new IllegalArgumentException("Agv dock id can't be empty.");
        }
        this.avgDockId = avgDockId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AgvDockId agvDockId = (AgvDockId) obj;
        return Objects.equals(avgDockId, agvDockId.avgDockId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(avgDockId);
    }

    @Override
    public int compareTo(AgvDockId o) {
        return this.compareTo(o);
    }

    @Override
    public String toString() {
        return avgDockId;
    }

    public String getIdentity() {
        return avgDockId;
    }
}
