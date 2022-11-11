package eapli.base.cartmanagement.application;

import eapli.base.cartmanagement.dto.CartItemDTO;
import eapli.base.orderServerAPI.CsvOrderProtocolProxy;
import eapli.base.orderServerAPI.FailedRequestException;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.service.ProductFilterService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.io.IOException;
import java.util.List;

public class CustomerCatalogSearchController {

    private CsvOrderProtocolProxy proxy;
    private ProductFilterService productFilterService = new ProductFilterService();
    SystemUser customer = AuthzRegistry.authorizationService().session().get().authenticatedUser();

    public CustomerCatalogSearchController(CsvOrderProtocolProxy proxy){
        this.proxy=proxy;
    }

    //CATALOG
    public void filterByCategory(String category) {
        productFilterService.filterByCategory(category);
    }

    public void filterByDescription(String description) {
        productFilterService.filterByDescription(description);
    }

    public void filterByBrand(String brand) {
        productFilterService.filterByBrand(brand);
    }

    public void resetFilters() {
        productFilterService.resetFilters();
    }

    public List<ProductDTO> filterResult(Integer sort) {
        return productFilterService.filterResult(sort);
    }



    //CART
    public void requestCartItemAddition(ProductDTO productDTO, Integer quantity) throws IOException {
        try {
            proxy.addCartItem(customer.email().toString(), productDTO.dtoId(), String.valueOf(quantity));
        } catch (FailedRequestException e) {
            throw new RuntimeException(e);
        }
    }


    public String cartCurrentProducts(){
        String result;
        try {
            result=proxy.cartItems(customer.email().toString());
        } catch (IOException | FailedRequestException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


}
