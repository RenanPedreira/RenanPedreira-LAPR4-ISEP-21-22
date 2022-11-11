package eapli.base.ordermanagement.dto;

import eapli.base.ordermanagement.domain.ClerkOrder;

public class ClerkOrderDTO {
    private String clerk;
    private String sourceChannel;
    private String comment;
    private String customer;
    private String billingAddress;
    private String deliveringAddress;
    private  String paymentMethod;

    public ClerkOrderDTO(ClerkOrder order){
        this.clerk = order.clerk();
        this.sourceChannel = order.sourceChannel();
        this.comment = order.comment();

        this.customer = order.customer();
        this.billingAddress = order.billingAddress();
        this.deliveringAddress = order.deliveringAddress();
        this.paymentMethod = order.paymentMethod();
        System.out.println("Trauma");
    }

    public String showInformation(){
        return String.format("Sales Clerk: %s" +
                "Customer: %s" +
                "Source Channel: %s" +
                "Comment: %s" +
                "Billing Address: %s" +
                "Delivering Address: %s" +
                "Payment Methods: %", clerk, customer, sourceChannel, comment, billingAddress, deliveringAddress, paymentMethod);
    }
}
