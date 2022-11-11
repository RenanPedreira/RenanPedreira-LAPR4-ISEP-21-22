package eapli.base.agvmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Battery implements ValueObject {

    @Column(name = "battery")
    private int battery;

    protected Battery() {
    }

    public Battery(int battery) {
        if (battery < 0){
            throw new IllegalArgumentException("The battery is not valid.");
        }
        this.battery = battery;
    }

    public int getBattery() {
        return battery;
    }

    @Override
    public String toString() {
        return "Battery: " + battery;
    }
}
