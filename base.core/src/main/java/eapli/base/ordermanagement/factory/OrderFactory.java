package eapli.base.ordermanagement.factory;

import eapli.base.agvmanagement.domain.Weight;
import eapli.base.common.domain.model.Address;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.ordermanagement.domain.*;
import eapli.base.productmanagement.domain.Producto.Price;
import eapli.framework.domain.model.DomainFactory;

import java.util.List;

public class OrderFactory implements DomainFactory<Ordre> {
    private static final String ADDRESS_TYPE1 = "Billing Address";
    private static final String ADDRESS_TYPE2 = "Delivery Address";

    private OrderNumericIdentifier identifier;
    private Customer customer;
    private Address billingAddress;
    private Address deliveryAddress;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    private List<OrderItem> itemList;
    private Price totalPrice;
    private Weight totalWeight;

    public void setIdentifier(int identifier) {
        this.identifier = new OrderNumericIdentifier(identifier);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBillingAddress(String streetName, String cityName, int doorNumber) {
        this.billingAddress = new Address(ADDRESS_TYPE1, streetName, cityName, doorNumber);
    }

    public void setDeliveryAddress(String streetName, String cityName, int doorNumber) {
        this.deliveryAddress = new Address(ADDRESS_TYPE2, streetName, cityName, doorNumber);
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = new PaymentMethod(paymentMethod);
    }

    public void setStatus(String status) {
        this.status = OrderStatus.valueOf(status);
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public void setTotalPrice(Price totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalWeight(Weight totalWeight) {
        this.totalWeight = totalWeight;
    }

    @Override
    public Ordre build() {
        return new Ordre(identifier, customer, billingAddress, deliveryAddress, paymentMethod, status, itemList, totalPrice, totalWeight);
    }
}
