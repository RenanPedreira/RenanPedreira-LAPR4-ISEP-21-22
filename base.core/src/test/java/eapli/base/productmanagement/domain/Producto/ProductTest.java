package eapli.base.productmanagement.domain.Producto;

import eapli.base.productmanagement.domain.Categorya.Category;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

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

        //FileReader file = new FileReader("Banana.jpg");
        File file = new File(poto);
        FileInputStream fileInputStream = new FileInputStream(file);
        long byteLength = file.length();

        byte[] photo = new byte[(int) byteLength];
        fileInputStream.read(photo, 0, (int) byteLength);*/

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


    @Test
    public void testToString() {
    }

    @Test
    public void getInternalCode() throws IOException {
        Product product = buildProduct();

        String result = product.getInternalCode();
        String expected = "AD18";

        assertEquals(result,expected);
    }
    @Test
    public void getName() throws IOException {
        Product product = buildProduct();

        String result = product.getName();
        String expected = "Banana";

        assertEquals(result,expected);
    }

    /*@Test
    public void getPhoto() throws IOException {

        Product product =buildProduct();


        Photo result = product.getPhoto();

        Photo expected = product.getPhoto();

        assertEquals(result,expected);

    }*/

    @Test
    public void getCategory() throws IOException {
        Product product = buildProduct();

        String result = product.getCategory();
        String expected = "ADL";

        assertEquals(result,expected);
    }

    @Test
    public void getCategoryAlpha() throws IOException {
        Product product = buildProduct();

        String result = product.getCategory();
        String expected = "ADL";

        assertEquals(result,expected);
    }

    @Test
    public void getBrand() throws IOException {
        Product product = buildProduct();

        String result = product.getBrand();
        String expected = "Madeira";

        assertEquals(result,expected);
    }

    @Test
    public void getShortDescription() throws IOException {
        Product product = buildProduct();

        String result = product.getShortDescription();
        String expected = "Robusto, Compacto";

        assertEquals(result,expected);
    }

    @Test
    public void getLongDescription() throws IOException {
        Product product = buildProduct();

        String result = product.getLongDescription();
        String expected = "Perfeito para Todas as idades mulheres homens todos podem usar!";

        assertEquals(result,expected);
    }

    @Test
    public void getTechnicalDescription() throws IOException {
        Product product = buildProduct();

        String result = product.getTechnicalDescription();
        String expected = "30 cm length , 8 cm width and made of plastic";

        assertEquals(result,expected);
    }


    @Test
    public void getProductionCode() throws IOException {
        Product product = buildProduct();

        String result = product.getProductionCode();
        String expected = "CH420";


        assertEquals(result,expected);
    }

    @Test
    public void getReference() throws IOException {
        Product product = buildProduct();

        String result = product.getReference();
        String expected = "A60";

        assertEquals(result,expected);
    }

    @Test
    public void getPrice() throws IOException {
        Product product = buildProduct();

        String result = product.getPrice();
        String expected = "12.0";

        assertEquals(result,expected);
    }

    @Test
    public void ensureCompleteProduct() {
        new Product(internalCode,productName,brand,technicalDescription,shortDescription,longDescription,reference,photo,productionCode,price,category,location,barcode);
        assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveCategory() {
        new Product(internalCode,productName,brand,technicalDescription,shortDescription,longDescription,reference,photo,productionCode,price,null,location,barcode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveIc() {
        new Product(null,productName,brand,technicalDescription,shortDescription,longDescription,reference,photo,productionCode,price,category,location,barcode);
    }



    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveName() {
        new Product(internalCode,null,brand,technicalDescription,shortDescription,longDescription,reference,photo,productionCode,price,category,location,barcode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveBrand() {
        new Product(internalCode,productName,null,technicalDescription,shortDescription,longDescription,reference,photo,productionCode,price,category,location,barcode);
    }



    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveSd() {
        new Product(internalCode,productName,brand,technicalDescription,null,longDescription,reference,photo,productionCode,price,category,location,barcode);
    }


    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHavePrice() {
        new Product(internalCode,productName,brand,technicalDescription,shortDescription,longDescription,reference,photo,productionCode,null,category,location,barcode);
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void ensureMustHavePhoto() {
        new Product(internalCode,productName,brand,technicalDescription,shortDescription,longDescription,reference,null,productionCode,price,category);
    }*/
    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveLocation() {
        new Product(internalCode,productName,brand,technicalDescription,shortDescription,longDescription,reference,photo,productionCode,price,category,null);
    }

}