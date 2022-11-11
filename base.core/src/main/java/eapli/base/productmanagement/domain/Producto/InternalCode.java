package eapli.base.productmanagement.domain.Producto;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class InternalCode implements ValueObject, Comparable<InternalCode> {

    @Column(name = "internalCode")
    private String internalCode;

    public InternalCode(){
    }

    public InternalCode(final String internalCode) {
        changeIC(internalCode);
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void changeIC(String internalCode){
        if (internalCode == null || internalCode.length() > 23) {
            throw new IllegalArgumentException("Internal Code max 23 alphanumeric and not empty.");
        }
        this.internalCode = internalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalCode that = (InternalCode) o;
        return Objects.equals(internalCode, that.internalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalCode);
    }

    @Override
    public String toString(){
        return internalCode;
    }

    @Override
    public int compareTo(InternalCode o) {
        return this.internalCode.compareTo(o.toString());
    }
}

