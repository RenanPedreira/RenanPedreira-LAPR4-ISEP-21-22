package eapli.base.productmanagement.domain.Producto;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TechnicalDescription implements ValueObject {

    @Column(name = "technicalDescription")
    private String techinalDescription;

    public TechnicalDescription(){
    }

    public TechnicalDescription(final String techinalDescription) {
        changeTd(techinalDescription);
    }
    public void changeTd(String techinalDescription){
        if (techinalDescription == null) {
            throw new IllegalArgumentException("technical description cant be null.");
        }
        this.techinalDescription = techinalDescription;
    }

    public String getTechinalDescription() {
        return techinalDescription;
    }

    @Override
    public String toString(){
        return techinalDescription;
    }

}
