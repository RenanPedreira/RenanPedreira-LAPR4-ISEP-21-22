package eapli.base.productmanagement.domain.Producto;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ShortDescription implements ValueObject {

    @Column(name = "shortDescription")
    private String shortDescription;

    public ShortDescription(){
    }

    public ShortDescription(final String shortDescription) {
        changeSd(shortDescription);
    }
    public void changeSd(String shortDescription){
        if ( shortDescription.length() > 30 || shortDescription ==null) {
            throw new IllegalArgumentException("Short Description needs to be 30 max and not empty.");
        }
        this.shortDescription = shortDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    @Override
    public String toString(){
        return shortDescription;
    }

}
