package eapli.base.ordermanagement.application;

import eapli.base.ordermanagement.dto.ClerkOrderDTO;
import eapli.base.ordermanagement.dto.OrderItemDTO;
import eapli.base.ordermanagement.service.CreateOrderService;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.service.ProductFilterService;

import java.util.List;

public class CreateOrderForClientController {

    ProductFilterService productFilterService = new ProductFilterService();
    CreateOrderService createOrderService = new CreateOrderService();

    public ClerkOrderDTO createOrderForClient(String sourceChannel, String comment, String paymentMethod, String clientEmail, String[] billingAddress, String[] deliveringAddress){
        ClerkOrderDTO order=createOrderService.createOrderForClient(sourceChannel, comment, paymentMethod, clientEmail, billingAddress, deliveringAddress);
        return order;
    }

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

    public void createOrderItem(ProductDTO product, Integer quantity) {
        createOrderService.createOrderItem(product, quantity);
    }

    public List<OrderItemDTO> getAllOrderItensAdded() {
        return createOrderService.getAllOrderItensAdded();
    }

    public void confirmOrder() {
        createOrderService.confrimOrder();
    }
}
