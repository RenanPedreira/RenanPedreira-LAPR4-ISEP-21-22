package eapli.base.agvmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Duration;

@Embeddable
public class AutonomyStatus implements ValueObject, Comparable<AutonomyStatus> {
    @Column(name = "BatteryDuration")
    private Duration duration;

    public AutonomyStatus(Duration duration) {
        if (duration.isNegative())
            throw new IllegalArgumentException("The duration \"" + duration + "\" isn't a valid duration.");

        this.duration = duration;
    }

    protected AutonomyStatus() {}

    @Override
    public int compareTo(AutonomyStatus otherStatus) {
        return duration.compareTo(otherStatus.duration);
    }

    @Override
    public String toString() {
        return duration.toString();
    }

    public Duration getDuration() {
        return duration;
    }
}
