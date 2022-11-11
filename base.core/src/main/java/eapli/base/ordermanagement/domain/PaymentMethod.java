package eapli.base.ordermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Renan Pedreira
 */
@Embeddable
public class PaymentMethod implements ValueObject {
    @Column(name = "paymentMethod")
    private String paymentMethod;

    public PaymentMethod(String paymentMethod){
        if (StringPredicates.isNullOrWhiteSpace(paymentMethod)){
            throw new IllegalArgumentException("Invalid payment method.");
        }
        this.paymentMethod=paymentMethod;
    }

    public PaymentMethod(){}

    @Override
    public String toString() {
        return paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
