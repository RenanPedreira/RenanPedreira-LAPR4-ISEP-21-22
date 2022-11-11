package eapli.base.warehousemanagement.domain.Aisle;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Square implements ValueObject {

    @Column(name = "lsquare")
    private long lsquare;
    @Column(name = "wsquare")
    private long wsquare;

    public Square() {
    }

    public Square(long lsquare, long wsquare) {
        this.lsquare = lsquare;
        this.wsquare = wsquare;
    }

    @Override
    public String toString() {
        return "length: " + lsquare + " width: " + wsquare;
    }
}
