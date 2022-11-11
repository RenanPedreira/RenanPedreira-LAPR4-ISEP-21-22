package eapli.base.productmanagement.domain.Producto;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Price implements ValueObject{

    @Column(name = "price")
    private Double price;

    public Price(){
    }


    public Price(final double price) {
         changePrice(price);
    }
   public void changePrice(Double price){
       if (price < 0) {
           throw new IllegalArgumentException("Price must not be empty or negative.");
       }
       this.price = price;
   }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return String.valueOf(price);
    }
}
