package eapli.base.productmanagement.domain.Producto;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Barcode {

    @Column(name = "Barcode")
    private String internalCode;

    public Barcode(){
    }

    public Barcode (final String internalCode) {
        changeIC(internalCode);
    }

    public String code() {
        return internalCode;
    }

    public void changeIC(String internalCode){
        if (internalCode == null || internalCode.length() > 13) {
            throw new IllegalArgumentException("BarCode max 13 alphanumeric and not empty.");
        }
        this.internalCode = internalCode;
    }

    @Override
    public String toString(){
        return internalCode;
    }

}
