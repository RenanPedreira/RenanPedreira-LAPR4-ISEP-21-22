package eapli.base.productmanagement.domain.Producto;

import eapli.base.productmanagement.domain.Categorya.Category;
import eapli.framework.domain.model.DomainFactory;
import org.springframework.boot.logging.logback.LogbackLoggingSystem;

public class ProductFactory implements DomainFactory<Product> {

    private Product prod;
    private ProductName name;
    private Brand brand;
    private Category category;
    private Price price;
    private Photo photo;
    private LongDescription longDescription;
    private ShortDescription shortDescription;
    private TechnicalDescription technicalDescription;
    private InternalCode internalCode;
    private ProductionCode productionCode;
    private Reference reference;
    private Location location;
    private Barcode barcode;

    public ProductFactory brand(final Brand brand){
        this.brand = brand;
        return this;
    }
    public ProductFactory name(final ProductName name){
        this.name = name;
        return this;
    }
    public ProductFactory category(final Category category){
        this.category = category;
        return this;
    }
    public ProductFactory price(final Price price){
        this.price = price;
        return this;
    }
    public ProductFactory photo(final Photo photo){
        this.photo = photo;
        return this;
    }
    public ProductFactory internal(final InternalCode internalCode){
        this.internalCode = internalCode;
        return this;
    }
    public ProductFactory prodCode(final ProductionCode productionCode){
        this.productionCode = productionCode;
        return this;
    }
    public ProductFactory reference(final Reference reference){
        this.reference= reference;
        return this;
    }
    public ProductFactory longD(final LongDescription longDescription){
        this.longDescription = longDescription;
        return this;
    }
    public ProductFactory shortD(final ShortDescription shortDescription){
        this.shortDescription = shortDescription;
        return this;
    }
    public ProductFactory tecD(final TechnicalDescription technicalDescription){
        this.technicalDescription = technicalDescription;
        return this;
    }
    public ProductFactory location(final Location location){
        this.location = location;
        return this;
    }
    public ProductFactory barCode(final Barcode barcode){
        this.barcode = barcode;
        return this;
    }


    private Product buildOrThrow() {
        if (prod != null) {
            return prod;
        } else if (brand != null && reference != null && price != null && shortDescription != null && longDescription != null && technicalDescription != null && internalCode != null && productionCode != null && category != null && name != null && location != null) {
            //prod = new Product(internalCode,name,brand,technicalDescription,shortDescription,longDescription,reference,photo,productionCode,price,category);
            prod = new Product(internalCode,name,brand,technicalDescription,shortDescription,longDescription,reference,photo,productionCode,price,category,location,barcode);
            return prod;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Product build() {
        final Product ret = buildOrThrow();
        // make sure we will create a new instance if someone reuses this builder and do not change
        // the previously built product.
        prod = null;
        return ret;
    }
}
