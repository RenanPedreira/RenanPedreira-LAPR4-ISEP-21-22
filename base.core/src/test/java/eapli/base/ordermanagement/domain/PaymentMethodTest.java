package eapli.base.ordermanagement.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class PaymentMethodTest {

    private static final String validPaymentMethod = "Bitcoin";
    private static final String invalidPaymentMethod1 = "";
    private static final String invalidPaymentMethod2 = "        ";

    @Test
    public void ensureCreatesWithValidPaymentMethod() {
        PaymentMethod paymentMethod = new PaymentMethod(validPaymentMethod);
        assertNotNull(paymentMethod);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveValidPaymentMethod() {
        new Comment(invalidPaymentMethod1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveNotNullPaymentMethod() {
        new Comment(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNotHaveEmptyPaymentMethod() {
        new Comment(invalidPaymentMethod2);
    }
}