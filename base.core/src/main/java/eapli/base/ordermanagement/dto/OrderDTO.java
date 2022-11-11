package eapli.base.ordermanagement.dto;

import java.util.Map;

public class OrderDTO {
    private int numericIdentifier;
    private String customerName;
    private String billingAddress;
    private String deliveryAddress;
    private String paymentMethod;
    private Map<String, Integer> items;
    private double totalPrice;
    private double totalWeight;
    private String orderStatus;

    public OrderDTO(int numericIdentifier,
                    String customerName,
                    String billingAddress,
                    String deliveryAddress,
                    String paymentMethod,
                    Map<String, Integer> items,
                    double totalPrice,
                    double totalWeight,
                    String orderStatus) {
        this.numericIdentifier = numericIdentifier;
        this.customerName = customerName;
        this.billingAddress = billingAddress;
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.items = items;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order: " +
                "Identifier =" + numericIdentifier +
                "; Customer Name ='" + customerName + '\'' +
                "; Billing Address ='" + billingAddress + '\'' +
                "; Delivery Address ='" + deliveryAddress + '\'' +
                "; Payment Method ='" + paymentMethod + '\'' +
                "; Items =" + items + '\'' +
                "; Weight =" + totalWeight + '\'' +
                "; Price =" + totalPrice + '\'';
    }

    public int getNumericIdentifier() {
        return numericIdentifier;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public String showInformation(){
        return "Order id: " + numericIdentifier +
                "\ncustomer name: " + customerName +
                "\nprice: " + totalPrice + " €" +
                "\nweight: " + totalWeight + " kg";
    }

    public String showStatusAndInformation(){
        return "Order id: " + numericIdentifier +
                "\ncustomer name: " + customerName +
                "\nstatus: " + orderStatus +
                "\nprice: " + totalPrice + " euros";
    }
}
