package eapli.base.warehousemanagement.domain.Assignment;

import eapli.base.agvmanagement.domain.*;

import eapli.base.common.domain.model.Identifier;
import eapli.base.ordermanagement.domain.Ordre;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Assignment implements Serializable, AggregateRoot<Identifier> {
    @EmbeddedId
    private Identifier identifier;

    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;

    @OneToOne()
    private Ordre order;

    @OneToOne()
    private AGV agv;

    public Assignment(Identifier identifier,
                AGV agv,
               AssignmentStatus status,
               Ordre order
               ) {
        Preconditions.noneNull(identifier,order,agv,status);
        this.identifier = identifier;
        this.agv =agv;
        this.status = status;
        this.order = order;
    }

    protected Assignment() {}

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Identifier identity() {
        return null;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Ordre getOrder() {
        return order;
    }

    public AGV getAgv() {
        return agv;
    }

    public AssignmentStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Assignment: " +
                "identifier=" + identifier +
                ", status=" + status +
                ", order=" + order +
                ", agv=" + agv;
    }
}
