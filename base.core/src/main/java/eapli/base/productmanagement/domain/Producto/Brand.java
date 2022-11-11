package eapli.base.productmanagement.domain.Producto;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Brand implements ValueObject {

    @Column(name = "brandName")
    private String brandName;

    public Brand(){
    }

    public Brand(final String brandName) {
        changeBrand(brandName);
    }

    public String getBrandName() {
        return brandName;
    }

    public void changeBrand(String brandName){
        if (brandName.length() > 50 || brandName == null) {
            throw new IllegalArgumentException("Brand needs to have 50 characters max and not empty.");
        }
        this.brandName = brandName;
    }

    @Override
    public String toString(){
        return brandName;
    }
}

