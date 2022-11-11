package eapli.base.productmanagement.domain.Producto;

import eapli.base.productmanagement.domain.Categorya.Category;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFactoryTest {


    private static final Price price = new  Price(12.0);

    private static final Category category = new Category("ADL", "Adulto");

    private static final InternalCode internalCode = new InternalCode("AD18");
    private static final ProductionCode productionCode = new ProductionCode("CH420");
    private static final Reference reference = new Reference("A60");
    private static final Brand brand = new Brand("Madeira");
    private static final ShortDescription shortDescription= new ShortDescription("Robusto, Compacto");
    private static final LongDescription longDescription = new LongDescription("Perfeito para Todas as idades mulheres homens todos podem usar!");
    private static final TechnicalDescription technicalDescription = new TechnicalDescription("30 cm length , 8 cm width and made of plastic");
    private static final ProductName productName = new ProductName("Banana");
    private static final Photo photo = new Photo();
    private static final Location location = new Location("porto");
    private static final Barcode barcode = new Barcode("UPS2001");

    private Product buildProduct() throws IOException {
        /*final String poto = "C:\\Users\\biach\\OneDrive\\Documents\\lei21_22_s4_2dj_3\\Imagens\\Banana.jpg";

        File file = new File(poto);
        FileInputStream fileInputStream = new FileInputStream(file);
        long byteLength = file.length();

        byte[] photo = new byte[(int) byteLength];
        fileInputStream.read(photo, 0, (int) byteLength);

*/

        Product product = new ProductFactory().
                brand(new Brand(brand.toString()))
                .photo(null)
                .location(location)
                .price(new Price(price.getPrice()))
                .reference(new Reference(reference.getReference()))
                .category(category)
                .barCode(barcode)
                .internal(new InternalCode(internalCode.getInternalCode()))
                .prodCode(new ProductionCode(productionCode.getProductionCode()))
                .longD(new LongDescription(longDescription.getLongDescription()))
                .shortD(new ShortDescription(shortDescription.getShortDescription()))
                .tecD(new TechnicalDescription(technicalDescription.getTechinalDescription())).name(new ProductName(productName.toString())).build();

        return product;
    }

    private byte[] getPhoto() throws IOException {
        final String poto = "C:\\Users\\biach\\OneDrive\\Documents\\lei21_22_s4_2dj_3\\Imagens\\Banana.jpg";

        File file = new File(poto);
        FileInputStream fileInputStream = new FileInputStream(file);
        long byteLength = file.length();

        byte[] photo = new byte[(int) byteLength];
        fileInputStream.read(photo, 0, (int) byteLength);

        return photo;
    }


    @Test
    public void ensureBuildProduct() throws IOException {
        Product product = buildProduct();
        assertNotNull(product);
    }

    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveCategory() throws IOException {

        new ProductFactory().
                brand(new Brand(brand.toString()))
                .photo(null)
                .location(location)
                .price(new Price(price.getPrice()))
                .reference(new Reference(reference.getReference()))
                .category(null)
                .barCode(barcode)
                .internal(new InternalCode(internalCode.getInternalCode()))
                .prodCode(new ProductionCode(productionCode.getProductionCode()))
                .longD(new LongDescription(longDescription.getLongDescription()))
                .shortD(new ShortDescription(shortDescription.getShortDescription()))
                .tecD(new TechnicalDescription(technicalDescription.getTechinalDescription())).name(new ProductName(productName.toString())).build();

    }

    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveBrand() throws IOException {

        new ProductFactory().
                brand(null)
                .photo(null)
                .location(location)
                .price(new Price(price.getPrice()))
                .reference(new Reference(reference.getReference()))
                .category(category)
                .barCode(barcode)
                .internal(new InternalCode(internalCode.getInternalCode()))
                .prodCode(new ProductionCode(productionCode.getProductionCode()))
                .longD(new LongDescription(longDescription.getLongDescription()))
                .shortD(new ShortDescription(shortDescription.getShortDescription()))
                .tecD(new TechnicalDescription(technicalDescription.getTechinalDescription())).name(new ProductName(productName.toString())).build();

    }

   /* @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHavePhoto(){

        new ProductFactory().
                brand(new Brand(brand.toString()))
                .photo(null)
                .price(new Price(price.getPrice()))
                .reference(new Reference(reference.getReference()))
                .category(category)
                .internal(new InternalCode(internalCode.getInternalCode()))
                .prodCode(new ProductionCode(productionCode.getProductionCode()))
                .longD(new LongDescription(longDescription.getLongDescription()))
                .shortD(new ShortDescription(shortDescription.getShortDescription()))
                .tecD(new TechnicalDescription(technicalDescription.getTechinalDescription())).name(new ProductName(productName.toString())).build();

    }*/

    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHavePrice() throws IOException {

        new ProductFactory().
                brand(new Brand(brand.toString()))
                .photo(null)
                .location(location)
                .price(null)
                .barCode(barcode)
                .reference(new Reference(reference.getReference()))
                .category(category)
                .internal(new InternalCode(internalCode.getInternalCode()))
                .prodCode(new ProductionCode(productionCode.getProductionCode()))
                .longD(new LongDescription(longDescription.getLongDescription()))
                .shortD(new ShortDescription(shortDescription.getShortDescription()))
                .tecD(new TechnicalDescription(technicalDescription.getTechinalDescription())).name(new ProductName(productName.toString())).build();

    }

    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveReference() throws IOException {

        new ProductFactory().
                brand(new Brand(brand.toString()))
                .photo(null)
                .location(location)
                .price(new Price(price.getPrice()))
                .reference(null)
                .barCode(barcode)
                .category(category)
                .internal(new InternalCode(internalCode.getInternalCode()))
                .prodCode(new ProductionCode(productionCode.getProductionCode()))
                .longD(new LongDescription(longDescription.getLongDescription()))
                .shortD(new ShortDescription(shortDescription.getShortDescription()))
                .tecD(new TechnicalDescription(technicalDescription.getTechinalDescription())).name(new ProductName(productName.toString())).build();

    }

    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveIc() throws IOException {

        new ProductFactory().
                brand(new Brand(brand.toString()))
                .photo(null)
                .location(location)
                .price(new Price(price.getPrice()))
                .reference(new Reference(reference.getReference()))
                .category(category)
                .internal(null)
                .barCode(barcode)
                .prodCode(new ProductionCode(productionCode.getProductionCode()))
                .longD(new LongDescription(longDescription.getLongDescription()))
                .shortD(new ShortDescription(shortDescription.getShortDescription()))
                .tecD(new TechnicalDescription(technicalDescription.getTechinalDescription())).name(new ProductName(productName.toString())).build();

    }

    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHavePc() throws IOException {

        new ProductFactory().
                brand(new Brand(brand.toString()))
                .photo(null)
                .location(location)
                .barCode(barcode
                )
                .price(new Price(price.getPrice()))
                .reference(new Reference(reference.getReference()))
                .category(category)
                .internal(new InternalCode(internalCode.getInternalCode()))
                .prodCode(null)
                .longD(new LongDescription(longDescription.getLongDescription()))
                .shortD(new ShortDescription(shortDescription.getShortDescription()))
                .tecD(new TechnicalDescription(technicalDescription.getTechinalDescription())).name(new ProductName(productName.toString())).build();

    }

    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveLd() throws IOException {

        new ProductFactory().
                brand(new Brand(brand.toString()))
                .photo(null)
                .location(location)
                .barCode(barcode)
                .price(new Price(price.getPrice()))
                .reference(new Reference(reference.getReference()))
                .category(category)
                .internal(new InternalCode(internalCode.getInternalCode()))
                .prodCode(new ProductionCode(productionCode.getProductionCode()))
                .longD(null)
                .shortD(new ShortDescription(shortDescription.getShortDescription()))
                .tecD(new TechnicalDescription(technicalDescription.getTechinalDescription())).name(new ProductName(productName.toString())).build();

    }

    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveSd() throws IOException {

        new ProductFactory().
                brand(new Brand(brand.toString()))
                .photo(null)
                .location(location)
                .price(new Price(price.getPrice()))
                .reference(new Reference(reference.getReference()))
                .category(category)
                .barCode(barcode)
                .internal(new InternalCode(internalCode.getInternalCode()))
                .prodCode(new ProductionCode(productionCode.getProductionCode()))
                .longD(new LongDescription(longDescription.getLongDescription()))
                .shortD(null)
                .tecD(new TechnicalDescription(technicalDescription.getTechinalDescription())).name(new ProductName(productName.toString())).build();

    }

    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveTd() throws IOException {

        new ProductFactory().
                brand(new Brand(brand.toString()))
                .photo(null)
                .location(location)
                .barCode(barcode)
                .price(new Price(price.getPrice()))
                .reference(new Reference(reference.getReference()))
                .category(category)
                .internal(new InternalCode(internalCode.getInternalCode()))
                .prodCode(new ProductionCode(productionCode.getProductionCode()))
                .longD(new LongDescription(longDescription.getLongDescription()))
                .shortD(new ShortDescription(shortDescription.getShortDescription()))
                .tecD(null).name(new ProductName(productName.toString())).build();

    }

    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveName() throws IOException {

        new ProductFactory().
                brand(new Brand(brand.toString()))
                .photo(null)
                .location(location)
                .barCode(barcode)
                .price(new Price(price.getPrice()))
                .reference(new Reference(reference.getReference()))
                .category(category)
                .internal(new InternalCode(internalCode.getInternalCode()))
                .prodCode(new ProductionCode(productionCode.getProductionCode()))
                .longD(new LongDescription(longDescription.getLongDescription()))
                .shortD(new ShortDescription(shortDescription.getShortDescription()))
                .tecD(new TechnicalDescription(technicalDescription.getTechinalDescription())).name(null).build();

    }

    @Test(expected = IllegalStateException.class)
    public void ensureBuildMustHaveLocation() throws IOException {

        new ProductFactory().
                brand(new Brand(brand.toString()))
                .photo(null)
                .barCode(barcode)
                .location(null)
                .price(new Price(price.getPrice()))
                .reference(new Reference(reference.getReference()))
                .category(category)
                .internal(new InternalCode(internalCode.getInternalCode()))
                .prodCode(new ProductionCode(productionCode.getProductionCode()))
                .longD(new LongDescription(longDescription.getLongDescription()))
                .shortD(new ShortDescription(shortDescription.getShortDescription()))
                .tecD(new TechnicalDescription(technicalDescription.getTechinalDescription())).name(null).build();

    }

}