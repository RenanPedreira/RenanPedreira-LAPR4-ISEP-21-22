package eapli.base.productmanagement.dto;

import eapli.base.productmanagement.domain.Producto.InternalCode;
import eapli.base.productmanagement.domain.Producto.Product;

/**
 *
 * @author Renan Pedreira
 */
public class ProductDTO {
    private String id;

    private String name;

    private String longDescription;

    //private byte[] photo;

    private String productionCode;

    private String reference;

    private String shortDescription;

    private String technicalDescription;

    private String brand;

    private String category;

    private String price;

    public ProductDTO(Product p){
        this.id = p.getInternalCode();
        this.name = p.getName();
        this.category = p.getCategory();
        this.brand = p.getBrand();
        this.shortDescription = p.getShortDescription();
        this.longDescription = p.getLongDescription();
        this.technicalDescription = p.getTechnicalDescription();
        this.productionCode = p.getProductionCode();
        this.reference = p.getReference();
        this.price = p.getPrice();
        //this.photo = p.getPhoto().getPhoto();
    }

    public ProductDTO(Product p, String a){
        this.id = p.getInternalCode();
        this.name = p.getName();
        this.category = p.getCategory();
        this.brand = p.getBrand();
        this.shortDescription = p.getShortDescription();
        this.longDescription = p.getLongDescription();
        this.technicalDescription = p.getTechnicalDescription();
        this.productionCode = p.getProductionCode();
        this.reference = p.getReference();
        this.price = p.getPrice();
        //this.photo = p.getPhoto().getPhoto();
    }

    public String showInformation(){
        return String.format("Internal Code: %s\n" +
                "Name: %s\n" +
                "Description: %s\n" +
                "Brand: %s\n" +
                "Price: %s\n", id, name, shortDescription, brand, price);
    }

    public String showBasicInformation(){
        return String.format("Internal Code: %s\n" +
                "Name: %s\n" +
                "Description: %s\n" +
                "Category: %s\n" +
                "Brand: %s\n" +
                "Price: %s\n", id, name, shortDescription, category, brand, price);
    }

    public String showAllInformation(){
        return String.format("Internal Code: %s\n" +
                "Name: %s\n" +
                "Short Description: %s\n" +
                "Long Description: %s\n" +
                "Technical Description: %s\n" +
                "Category: %s\n" +
                "Brand: %s\n" +
                "Price: %s\n" +
                "Production Code: %s\n" +
                "Reference: %s\n", id, name, shortDescription, longDescription, technicalDescription,category, brand, price, productionCode, reference);
    }

    public String dtoId(){
        return this.id;
    }
}
