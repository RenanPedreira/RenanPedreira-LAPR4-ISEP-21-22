package eapli.base.productmanagement.service;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Categorya.Category;
import eapli.base.productmanagement.domain.Producto.*;
import eapli.base.productmanagement.dto.CategoryDto;
import eapli.base.productmanagement.persistence.ProductRepository;
import eapli.framework.domain.repositories.TransactionalContext;


public class ProductProductionService {

    private final TransactionalContext txCtx = PersistenceContext.repositories().newTransactionalContext();
    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Product buildProduct(byte[] photo, String internalCode, String shortDescription, String extendedDescription, String brand, String reference, String productionCode, String technicalDescription, Category category, double price, String name,String location,String barcode){
        final var product = new ProductFactory().
        brand(new Brand(brand))
        .photo(new Photo(photo))
        .price(new Price(price))
                .barCode(new Barcode(barcode))
        .reference(new Reference(reference))
        .category(category)
        .internal(new InternalCode(internalCode))
        .prodCode(new ProductionCode(productionCode))
        .longD(new LongDescription(extendedDescription))
        .shortD(new ShortDescription(shortDescription)).location(new Location(location))
        .tecD(new TechnicalDescription(technicalDescription)).name(new ProductName(name)).build();

        return productRepository.save(product);
    }

    
}
