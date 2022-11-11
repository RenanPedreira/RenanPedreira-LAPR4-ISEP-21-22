package eapli.base.agvmanagement.factory;

import eapli.base.agvmanagement.domain.*;
import eapli.base.common.domain.model.Description;
import eapli.base.common.domain.model.Identifier;
import eapli.base.common.domain.model.IdentifierGenerator;
import eapli.base.warehousemanagement.domain.AgvDock.AgvDock;
import eapli.framework.domain.model.DomainFactory;

import java.time.Duration;

public class AGVBuilder implements DomainFactory<AGV> {
    private static final String IDENTIFIER_PREFIX = "AGV";
    private static final String DESCRIPTION_PREFIX = "TECHNICAL_DESCRIPTION";

    private Identifier identifier;
    private Model model;
    private Description technicalDescription;
    private TaskStatus taskStatus;
    private AutonomyStatus autonomyStatus;
    private AgvDock baseLocation;
    private Weight capacity;
    private Speed speed;

    private void setIdentifier(String identifier) {
        if (!identifier.contains(IDENTIFIER_PREFIX))
            throw new IllegalArgumentException("The Identifier \"" + identifier + "\" has an invalid format.");

        this.identifier = new Identifier(identifier);
    }

    public void setIdentifier() {
        this.identifier = new IdentifierGenerator().generateIdentifier();
    }

    public void setModel(String model) {
        this.model = new Model(model);
    }

    public void setTechnicalDescription(String technicalDescription) {
        this.technicalDescription = new Description(DESCRIPTION_PREFIX, technicalDescription);
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = TaskStatus.valueOf(taskStatus);
    }

    public void setAutonomyStatus(Duration autonomyStatus) {
        this.autonomyStatus = new AutonomyStatus(autonomyStatus);
    }

    public void setBaseLocation(AgvDock baseLocation) {
        this.baseLocation = baseLocation;
    }

    public void setCapacity(double capacity) {
        this.capacity = new Weight(capacity);
    }

    public void setSpeed(int speed) {
        this.speed = new Speed(speed);
    }

    @Override
    public AGV build() {
        if (speed == null)
            return new AGV(identifier,
                    model,
                    technicalDescription,
                    taskStatus,
                    autonomyStatus,
                    baseLocation,
                    capacity,
                    new Speed(1));
        else
            return new AGV(identifier,
                    model,
                    technicalDescription,
                    taskStatus,
                    autonomyStatus,
                    baseLocation,
                    capacity,
                    speed);
    }
}
