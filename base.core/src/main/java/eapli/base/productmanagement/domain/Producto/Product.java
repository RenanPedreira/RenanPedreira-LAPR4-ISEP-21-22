package eapli.base.productmanagement.domain.Producto;

import eapli.base.productmanagement.domain.Categorya.Category;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Product implements Serializable, AggregateRoot<InternalCode> {

    @EmbeddedId
    private InternalCode id;

    @Embedded
    private Location location;

    @Embedded
    private ProductName name;

    @Embedded
    private LongDescription longDescription;

    @Embedded
    private Photo photo;

    @Embedded
    private ProductionCode productionCode;

    @Embedded
    private Reference reference;

    @Embedded
    private ShortDescription shortDescription;

    @Embedded
    private TechnicalDescription technicalDescription;

    @Embedded
    private Brand brand;

    @Embedded
    private Price price;

    @Embedded
    private Barcode barcode;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    private Category category;

    public Product(){
    }

    public Product(InternalCode id, ProductName name, Brand brand , TechnicalDescription technicalDescription, ShortDescription shortDescription, LongDescription longDescription, Reference reference, Photo photo, ProductionCode productionCode,Price price,Category category,Barcode barcode){
        Preconditions.noneNull(name,photo,id, brand, technicalDescription, shortDescription, longDescription, reference,productionCode,price,category,barcode);
        this.id = id;
        this.name = name;
        this.longDescription = longDescription;
        this.productionCode = productionCode;
        this.reference = reference;
        this.shortDescription = shortDescription;
        this.technicalDescription = technicalDescription;
        this.photo = photo;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.barcode = barcode;
    }

    public Product(InternalCode id, ProductName name, Brand brand , TechnicalDescription technicalDescription, ShortDescription shortDescription, LongDescription longDescription, Reference reference, Photo photo, ProductionCode productionCode,Price price,Category category,Location location,Barcode barcode){
        Preconditions.noneNull(name,id, brand, shortDescription,price,category,location,barcode);
        this.id = id;
        this.name = name;
        this.longDescription = longDescription;
        this.productionCode = productionCode;
        this.reference = reference;
        this.shortDescription = shortDescription;
        this.technicalDescription = technicalDescription;
        this.photo = photo;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.location = location;
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", location=" + location +
                ", name=" + name +
                ", longDescription=" + longDescription +
                ", photo=" + photo +
                ", productionCode=" + productionCode +
                ", reference=" + reference +
                ", shortDescription=" + shortDescription +
                ", technicalDescription=" + technicalDescription +
                ", brand=" + brand +
                ", price=" + price +
                ", barcode=" + barcode +
                ", category=" + category +
                '}';
    }

    public String getInternalCode(){
        return this.id.toString();
    }

    public String getName(){
        return this.name.toString();
    }

    public Photo getPhoto() {
        return photo;
    }

    public String getCategory(){
        return this.category.toString();
    }

    public String getBrand(){
        return this.brand.toString();
    }

    public String getShortDescription(){
        return this.shortDescription.toString();
    }

    public String getLongDescription(){
        return this.longDescription.toString();
    }

    public String getTechnicalDescription(){
        return this.technicalDescription.toString();
    }

    public String getProductionCode(){
        return this.productionCode.toString();
    }

    public String getReference(){
        return this.reference.toString();
    }

    public String getPrice(){
        return this.price.toString();
    }

    public String getLocation(){
        return this.location.getLocation();
    }

    public String getProductPosition(){
        return this.location.getPositionProduct();
    }

    public String getBarcode(){
        return this.barcode.toString();
    }

    public ProductName name() {
        return name;
    }

    public ProductDTO toDTO(){
        return new ProductDTO(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return  DomainEntities.areEqual(this, other);
    }

    @Override
    public InternalCode identity() {
        return this.id;
    }
}

