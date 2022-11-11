package eapli.base.agvmanagement.domain;

import eapli.base.common.util.Pair;
import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Random;

@Embeddable
public class Position implements ValueObject, Comparable<Position> {

    @Column(name = "xPosition")
    private int xPosition;
    @Column(name = "yPosition")
    private int yPosition;

    public Position(int xPosition, int yPosition) {
        Random rand = new Random();
        this.xPosition= rand.nextInt(50)+1;
        this.yPosition= rand.nextInt(50)+1;
    }

    public Position(int xPosition, int yPosition, int darkMagic) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public Position(){

    }

    public int getxPosition(){
        return this.xPosition;
    }

    public int getyPosition(){
        return this.yPosition;
    }

    public void setxPosition(int xPosition){
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition){
        this.yPosition = yPosition;
    }

    @Override
    public int compareTo(Position o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Position{" +
                "xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                '}';
    }

    public Pair<Integer, Integer> coordinates(){
        return new Pair<>(this.xPosition, this.yPosition);
    }
}
