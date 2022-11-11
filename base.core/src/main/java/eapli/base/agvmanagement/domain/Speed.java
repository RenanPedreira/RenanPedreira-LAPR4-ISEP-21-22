package eapli.base.agvmanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Speed implements ValueObject, Comparable<Speed> {

    private int speed;

    private static final int MAX_VELOCITY = 3;

    public Speed(){

    }

    public Speed(int speed){
        setSpeed(speed);
    }

    public int getSpeed(){
        return this.speed;
    }

    public void setSpeed(int speed){
        if (speed < 0 || speed > MAX_VELOCITY){
            throw new IllegalArgumentException("Invalid speed.");
        }
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Speed{" +
                "speed=" + speed +
                '}';
    }

    @Override
    public int compareTo(Speed o) {
        return 0;
    }
}
