package eapli.base.productmanagement.domain.Producto;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProductName implements ValueObject {
    @Column(name = "name")
    private String name;

    public ProductName(){
    }

    public ProductName(String name){
        changeName(name);
    }

    public void changeName(String name){
        if (name == null){
            throw new IllegalArgumentException("Name cant be null");
        }
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public String getName() {
        return name;
    }
}
