package eapli.base.productmanagement.domain.Categorya;

import eapli.framework.domain.model.AggregateRoot;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Category implements Serializable, AggregateRoot<Alpha> {
    @EmbeddedId
    private Alpha alpha;

    @Embedded
    private Description description;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "superCategory")
    private Category superCategory;

    public Category(String alpha,String description) {
        this.alpha = new Alpha(alpha);
        this.description = new Description(description);
    }

    public Category getSuperCategory() {
        return superCategory;
    }

    public Category(){};

    public Category(Alpha alpha,Description description, Category superCategory){
        this.alpha = alpha;
        this.description = description;
        this.superCategory=superCategory;
    }


    public String getAlpha() {
        return alpha.toString();
    }

    public String getDescription() { return description.toString();}

    @Override
    public String toString(){
        return alpha.toString();
    }

    @Override
    public boolean sameAs(Object other) {
        return this.alpha.equals(other.toString());
    }

    @Override
    public Alpha identity() {
        return this.alpha;
    }

    public String description(){
        return this.description.getDescription();
    }
}
