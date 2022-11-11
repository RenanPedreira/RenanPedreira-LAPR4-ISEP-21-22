package eapli.base.productmanagement.domain.Producto;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Random;

@Embeddable
public class Location implements ValueObject {

    @Column(name = "location")
    private String location;

    @Column(name= "positionX")
    private Integer positionX;

    @Column(name= "positionY")
    private Integer positionY;

    public Location(){
    }

    public Location(final String location) {
        changeLocation(location);
        Random rand = new Random();
        this.positionX= rand.nextInt(50)+1;
        this.positionY= rand.nextInt(50)+1;
    }

    public String getLocation() {
        return location;
    }

    public void changeLocation(String location){
        if (location == null) {
            throw new IllegalArgumentException("Location cant be empty.");
        }
        this.location = location;
    }

    public String getPositionProduct(){
        return this.positionX+"-"+positionY;
    }

    @Override
    public String toString(){
        return location;
    }

}
