package eapli.base.productmanagement.application;



import eapli.base.productmanagement.domain.Categorya.Category;
import eapli.base.productmanagement.domain.Producto.Location;
import eapli.base.productmanagement.domain.Producto.Product;

import eapli.base.productmanagement.dto.CategoryDto;
import eapli.base.productmanagement.service.ListCategoryService;
import eapli.base.productmanagement.service.ProductProductionService;
import eapli.base.warehousemanagement.dto.RowDTO;
import eapli.base.warehousemanagement.service.ImportFileService;
import eapli.base.warehousemanagement.service.ListLocationFromWarehouseService;

import java.util.List;

public class SpecifyProductController {

    ListCategoryService svcCategory = new ListCategoryService();
    ProductProductionService svcProduct = new ProductProductionService();
    ListLocationFromWarehouseService svcLocation = new ListLocationFromWarehouseService();
    ImportFileService service = new ImportFileService();

    public List<CategoryDto> getProductCategories(){
        return svcCategory.productCategories();
    }

    public Product specifyProduct(byte[] photo, String internalCode, String shortDescription, String extendedDescription, String brand, String reference, String productionCode, String technicalDescription, CategoryDto category, Double price,String name , String location, String barcode){
        //return svcProduct.buildProduct(photo,internalCode,shortDescription,extendedDescription,brand,reference,productionCode,technicalDescription,this.getCategoryByDTO(category),price,name);

        return svcProduct.buildProduct(photo,internalCode,shortDescription,extendedDescription,brand,reference,productionCode,technicalDescription,this.getCategoryByDTO(category),price,name,location,barcode);
    }



    public List<RowDTO> showLocation() {


        return svcLocation.listRow();
    }

    public Location getLocation(RowDTO row){

        return new Location(svcLocation.getRealRow(row).toString());
    }

    public Category getCategoryByDTO(CategoryDto categoryDto){
        return svcCategory.getRealCategory(categoryDto);
    }



}
