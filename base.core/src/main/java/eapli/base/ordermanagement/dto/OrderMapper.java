package eapli.base.ordermanagement.dto;

import eapli.base.ordermanagement.domain.OrderItem;
import eapli.base.ordermanagement.domain.Ordre;

import java.util.HashMap;
import java.util.Map;

public class OrderMapper {
    public static OrderDTO toDTO(Ordre order) {
        StringBuilder builder = new StringBuilder();

        int numericIdentifier = order.getNumericIdentifier().getNumericIdentifier();
        String customerName = builder.append(order.getCustomer().systemUser().name().firstName()).append(", ")
                .append(order.getCustomer().systemUser().name().lastName()).toString();

        builder = new StringBuilder();

        String billingAddress = builder.append(order.getBillingAddress().getStreetName()).append(", ")
                .append(order.getBillingAddress().getDoorNumber()).append("\n")
                .append(order.getBillingAddress().getCityName()).toString();

        builder = new StringBuilder();

        String deliveryAddress = builder.append(order.getDeliveringAddress().getStreetName()).append(", ")
                .append(order.getDeliveringAddress().getDoorNumber()).append("\n")
                .append(order.getDeliveringAddress().getCityName()).toString();
        String paymentMethod = order.getPaymentMethod().getPaymentMethod();

        Map<String, Integer> itemList = new HashMap<>();

        for (OrderItem item : order.itemList())
            itemList.put(item.getProduct().name().getName(), item.getQuantity().getQuantity());

        double price = order.getTotalPrice().getPrice();
        double weight = order.getTotalWeight().getWeight();

        String orderStatus = order.getStatus().name();

        return new OrderDTO(numericIdentifier, customerName, billingAddress, deliveryAddress, paymentMethod, itemList, price, weight, orderStatus);
    }
}
