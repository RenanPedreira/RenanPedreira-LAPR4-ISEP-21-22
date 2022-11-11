package eapli.base.productmanagement.domain.Categorya;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Description implements ValueObject {

    @Column(name = "description")
    private String description;


    public Description(){
    }

    public Description(final String description) {
        if(description == null || description.length() > 57) {
            throw new IllegalArgumentException("Description max 57 characters and not empty");
        }
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    @Override
    public String toString() { return description; }




}
