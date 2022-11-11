package eapli.base.ordermanagement.domain;

import eapli.base.agvmanagement.domain.Weight;
import eapli.base.common.domain.model.Address;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.productmanagement.domain.Producto.Price;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Renan Pedreira
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "orderType", discriminatorType = DiscriminatorType.STRING)
public class Ordre implements Serializable, AggregateRoot<OrderNumericIdentifier> {

    @EmbeddedId
    private OrderNumericIdentifier numericIdentifier;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer")
    private Customer customer;

    @AttributeOverrides({
            @AttributeOverride(name = "addressType", column = @Column(name = "addressTypeB")),
            @AttributeOverride(name = "streetName", column = @Column(name = "streetNameB")),
            @AttributeOverride(name = "cityName", column = @Column(name = "cityNameB")),
            @AttributeOverride(name = "doorNumber", column = @Column(name = "doorNumberB")),
    })
    @Embedded
    private Address billingAddress;


    @AttributeOverrides({
            @AttributeOverride(name = "addressType", column = @Column(name = "addressTypeD")),
            @AttributeOverride(name = "streetName", column = @Column(name = "streetNameD")),
            @AttributeOverride(name = "cityName", column = @Column(name = "cityNameD")),
            @AttributeOverride(name = "doorNumber", column = @Column(name = "doorNumberD")),
    })
    @Embedded
    private Address deliveringAddress;

    @Embedded
    private PaymentMethod paymentMethod;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ordre")
    private List<OrderItem> orderItemList = new ArrayList<>();

    @Embedded
    private Price totalPrice;

    @Embedded
    private Weight totalWeight;

    public Ordre(OrderNumericIdentifier numericIdentifier,
                 Customer customer,
                 Address billingAddress,
                 Address deliveringAddress,
                 PaymentMethod paymentMethod,
                 Price totalPrice,
                 Weight totalWeight) {
        Preconditions.noneNull(numericIdentifier, customer, billingAddress,
                deliveringAddress, paymentMethod, totalPrice, totalWeight);
        this.numericIdentifier = numericIdentifier;
        this.customer = customer;
        this.billingAddress = billingAddress;
        this.deliveringAddress = deliveringAddress;
        this.paymentMethod = paymentMethod;
        this.status = OrderStatus.TOBEPREPARED;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
    }

    public Ordre(OrderNumericIdentifier numericIdentifier,
                 Customer customer,
                 Address billingAddress,
                 Address deliveringAddress,
                 PaymentMethod paymentMethod,
                 OrderStatus status,
                 List<OrderItem> itemList,
                 Price totalPrice,
                 Weight totalWeight) {
        this.numericIdentifier = numericIdentifier;
        this.customer = customer;
        this.billingAddress = billingAddress;
        this.deliveringAddress = deliveringAddress;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.orderItemList = itemList;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
    }

    public Ordre() {
    }

    @Override
    public String toString() {
        return "Order: " +
                "numericIdentifier=" + numericIdentifier +
                ", customer=" + customer +
                ", billingAddress=" + billingAddress +
                ", deliveringAddress=" + deliveringAddress +
                ", paymentMethod=" + paymentMethod +
                ", status=" + status +
                ", orderItemList=" + orderItemList +
                ", price=" + totalPrice +
                ", weight=" + totalWeight;
    }

    public OrderNumericIdentifier getNumericIdentifier() {
        return numericIdentifier;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Price getTotalPrice() {
        return totalPrice;
    }

    public Weight getTotalWeight() {
        return totalWeight;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Address getDeliveringAddress() {
        return deliveringAddress;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItem> itemList() {
        return orderItemList;
    }

    public String numericIdentifier() {
        return this.numericIdentifier.toString();
    }

    public String customer() {
        return this.customer.toString();
    }

    public String customerName() {
        return this.customer.systemUser().name().toString();
    }

    public String paymentMethod() {
        return this.paymentMethod.toString();
    }

    public String billingAddress() {
        return this.billingAddress.toString();
    }

    public String deliveringAddress() {
        return this.deliveringAddress.toString();
    }

    public void changeStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean sameAs(Object other) {
        return this.equals((Ordre) other);
    }

    @Override
    public OrderNumericIdentifier identity() {
        return this.numericIdentifier;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItemList.add(orderItem);
    }

    public void changeStatusToDispatchment() {
        status = OrderStatus.DISPATCHED;
    }

    public void changeStatusToDelivered() {
        status = OrderStatus.DELIVERED;
    }

}
