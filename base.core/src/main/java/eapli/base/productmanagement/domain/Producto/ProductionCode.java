package eapli.base.productmanagement.domain.Producto;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ProductionCode implements ValueObject {
    @Column(name = "productionCode")
    private String productionCode;

    public ProductionCode(){
    }

    public ProductionCode(final String productionCode) {
        changePc(productionCode);
    }
    public void changePc(String productionCode){
        if (productionCode == null || productionCode.length() > 23) {
            throw new IllegalArgumentException("Production Code max 23 alphanumeric and not empty.");
        }
        this.productionCode = productionCode;
    }

    public String getProductionCode() {
        return productionCode;
    }

    @Override
    public String toString(){
        return productionCode;
    }

}
