package eapli.base.ordermanagement.domain;

import eapli.base.agvmanagement.domain.Weight;
import eapli.base.common.domain.model.Address;

import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.productmanagement.domain.Producto.Price;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import javax.persistence.Embedded;

/**
 *
 * @author Renan Pedreira
 */
public class ClerkOrderBuilder implements DomainFactory<ClerkOrder> {

    private OrderNumericIdentifier numericIdentifier;

    private ClerkOrder clerkOrder;
    private SystemUser clerk;
    private SourceChannel sourceChannel;
    private Comment comment;
    private Customer customer;
    private Address billingAddress;
    private Address deliveringAddress;
    private PaymentMethod paymentMethod;
    private Price totalPrice;
    private Weight totalWeight;

    public ClerkOrderBuilder numericIdentifier(final OrderNumericIdentifier numericIdentifier){
        this.numericIdentifier = numericIdentifier;
        return this;
    }

    public ClerkOrderBuilder clerk(final SystemUser clerk){
        this.clerk = clerk;
        return this;
    }

    public ClerkOrderBuilder sourceChannel(final SourceChannel sourceChannel){
        this.sourceChannel = sourceChannel;
        return this;
    }

    public ClerkOrderBuilder comment(final Comment comment){
        this.comment = comment;
        return this;
    }

    public ClerkOrderBuilder customer(final Customer customer) {
        this.customer = customer;
        return this;
    }

    public ClerkOrderBuilder billingAddress(final Address billingAddress){
        this.billingAddress = billingAddress;
        return this;
    }

    public ClerkOrderBuilder deliveringAddress(final Address deliveringAddress){
        this.deliveringAddress = deliveringAddress;
        return this;
    }

    public ClerkOrderBuilder paymentMethod(final PaymentMethod paymentMethod){
        this.paymentMethod = paymentMethod;
        return this;
    }

    public ClerkOrderBuilder totalPrice(final Price totalPrice){
        this.totalPrice = totalPrice;
        return this;
    }

    public ClerkOrderBuilder totalWeight(final Weight totalWeight){
        this.totalWeight = totalWeight;
        return this;
    }

    private ClerkOrder buildOrThrow(){
        if (clerkOrder != null){
            return clerkOrder;
        }else if(numericIdentifier != null && clerk != null && sourceChannel != null && comment != null && customer != null && billingAddress != null
                && deliveringAddress != null && paymentMethod != null && totalPrice != null && totalWeight != null) {
            clerkOrder = new ClerkOrder(clerk, sourceChannel, comment, numericIdentifier, customer, billingAddress, deliveringAddress, paymentMethod, totalPrice, totalWeight);
            return clerkOrder;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public ClerkOrder build() {
        final ClerkOrder order = buildOrThrow();
        clerkOrder = null;
        return order;
    }
}
