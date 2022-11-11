package eapli.base.productmanagement.application;

import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.service.ProductFilterService;

import java.util.List;

/**
 *
 * @author Renan Pedreira
 */
public class ViewCatalogController {
    ProductFilterService serv = new ProductFilterService();

    public void filterByCategory(String category){
        serv.filterByCategory(category);
    }

    public void filterByDescription(String description){
        serv.filterByDescription(description);
    }

    public void filterByBrand(String brand){
        serv.filterByBrand(brand);
    }

    public void resetFilters(){
        serv.resetFilters();
    }

    public List<ProductDTO> filterResult(int sort){
        return serv.filterResult(sort);
    }
}
