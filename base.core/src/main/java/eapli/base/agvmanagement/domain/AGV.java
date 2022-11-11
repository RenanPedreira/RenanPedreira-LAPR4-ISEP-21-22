package eapli.base.agvmanagement.domain;

import eapli.base.agvmanagement.dto.AGVDTO;
import eapli.base.common.domain.model.Description;
import eapli.base.common.domain.model.Identifier;
import eapli.base.common.util.Pair;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDock;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.dto.DTOable;

import javax.persistence.*;

@Entity
public class AGV implements AggregateRoot<Identifier>, DTOable<AGVDTO> {
    @EmbeddedId
    private Identifier identifier;
    @Embedded
    private Model model;
    @Embedded
    private Description technicalDescription;
    @Column(name = "TaskStatus")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    @Embedded
    private AutonomyStatus autonomyStatus;
    @Embedded
    private Weight capacity;
    @Embedded
    private Position position;
    @Embedded
    private Speed speed;
    @OneToOne()
    private AgvDock baseLocation;

    public AGV(Identifier identifier,
               Model model,
               Description technicalDescription,
               TaskStatus taskStatus,
               AutonomyStatus autonomyStatus,
               AgvDock baseLocation,
               Weight capacity) {
        this.identifier = identifier;
        this.model = model;
        this.technicalDescription = technicalDescription;
        this.taskStatus = taskStatus;
        this.autonomyStatus = autonomyStatus;
        this.baseLocation = baseLocation;
        this.capacity = capacity;
        this.position = new Position(1, 1);
    }

    public AGV(Identifier identifier,
               Model model,
               Description technicalDescription,
               TaskStatus taskStatus,
               AutonomyStatus autonomyStatus,
               AgvDock baseLocation,
               Weight capacity,
               Speed speed) {
        this.identifier = identifier;
        this.model = model;
        this.technicalDescription = technicalDescription;
        this.taskStatus = taskStatus;
        this.autonomyStatus = autonomyStatus;
        this.baseLocation = baseLocation;
        this.capacity = capacity;
        this.speed = speed;
        this.position = new Position(1, 1);
    }

    protected AGV() {}

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Identifier identity() {
        return null;
    }

    @Override
    public AGVDTO toDTO() {
        Pair<Integer,Integer> pos = new Pair<>(position.getxPosition(),position.getyPosition());
        return new AGVDTO(identifier.getIdentifier(),
                model.getModel(),
                technicalDescription.getDescription(),
                taskStatus.name(),
                autonomyStatus.getDuration(),
                baseLocation.identity().getIdentity(),
                capacity.getWeight(),speed.getSpeed(),pos);
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public Model getModel() {
        return model;
    }

    public Description getTechnicalDescription() {
        return technicalDescription;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public AutonomyStatus getAutonomyStatus() {
        return autonomyStatus;
    }

    public AgvDock getBaseLocation() {
        return baseLocation;
    }

    public Weight getCapacity() {
        return capacity;
    }

    public Speed getSpeed(){
        return speed;
    }

    public void changeSpeed(Speed speed){
        this.speed = speed;

    }

    public Position getPosition(){return position; }

    public void changePosition(Pair<Integer, Integer> position){
        this.position = new Position(position.getKey(), position.getValue(), 0);
    }

    public void changeStatus(TaskStatus status) {
        this.taskStatus = status;
    }

    public void changeStatus2(String status) {
        if(status.compareTo("FREE")==0)
            this.taskStatus = TaskStatus.FREE;
        if(status.compareTo("CHARGING")==0)
            this.taskStatus = TaskStatus.CHARGING;
        if(status.compareTo("ASSIGNED")==0)
            this.taskStatus = TaskStatus.ASSIGNED;
    }

}