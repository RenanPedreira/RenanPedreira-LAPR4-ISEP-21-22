package eapli.base.productmanagement.domain.Producto;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Reference implements ValueObject {

    @Column(name = "reference")
    private String reference;

    public Reference(){
    }

    public Reference(final String reference) {
        changeReference(reference);
    }

    public void changeReference(String reference){
        if (reference == null || reference.length() > 23) {
            throw new IllegalArgumentException("Reference max 23 alphanumeric and not empty.");
        }
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    @Override
    public String toString(){
        return reference;
    }

}
