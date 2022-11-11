package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Renan Pedreira
 */
@Embeddable
public class Quantity implements ValueObject {
    @Column(name = "quantity")
    private Integer quantity;

    public Quantity(Integer quantity){
        if(quantity>0)
            this.quantity = quantity;
        else
            throw new IllegalArgumentException("Quantity needs to be one ore more units.");
    }

    public Quantity(){}

    public String toString(){
        return String.valueOf(this.quantity);
    }

    public Integer getQuantity() {
        return quantity;
    }
}
