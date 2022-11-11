package eapli.base.ordermanagement.domain;

import eapli.base.agvmanagement.domain.Weight;
import eapli.base.common.domain.model.Address;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.productmanagement.domain.Producto.Price;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;


import javax.persistence.*;

/**
 *
 * @author Renan Pedreira
 */
@Entity
@DiscriminatorValue("clerk")
public class ClerkOrder extends Ordre{
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "clerk")
    private SystemUser clerk;
    @Embedded
    private SourceChannel sourceChannel;
    @Embedded
    private Comment comment;

    public ClerkOrder(SystemUser clerk, SourceChannel sourceChannel, Comment comment, OrderNumericIdentifier numericIdentifier, Customer customer, Address billingAddress, Address deliveringAddress, PaymentMethod paymentMethod, Price totalPrice,
                      Weight totalWeight){
        super(numericIdentifier, customer, billingAddress, deliveringAddress, paymentMethod, totalPrice, totalWeight);
        this.clerk = clerk;
        this.sourceChannel = sourceChannel;

        if(comment.equals("")){
            this.comment = new Comment("No comment");
        }else{
            this.comment = comment;
        }
    }

    public ClerkOrder(){}

    @Override
    public String toString() {
        return String.format("%s\n" +
                "Clerk %s\n" +
                "Source Channel %s\n" +
                "Comments %s", super.toString(), clerk, sourceChannel, comment);
    }

    public String clerk(){
        return clerk.email().toString();
    }

    public String comment() {
        return comment.toString();
    }

    public String sourceChannel() {
        return sourceChannel.toString();
    }
}
