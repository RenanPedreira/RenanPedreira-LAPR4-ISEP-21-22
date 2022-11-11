package eapli.base.customermanagement.domain.model;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Data Structure which represents the Domain Concept "Phone Number" (c.f. Order_System.png)
 *
 * @author Gonçalo Monteiro
 */
@Embeddable
public class PhoneNumber implements ValueObject, Comparable<PhoneNumber> {
    private static final String REGION_CODE = "PT";

    @Column(name = "Phone_Number")
    private String phoneNumber;

    public PhoneNumber(String phoneNumber) throws IllegalArgumentException {
        Preconditions.nonNull(phoneNumber);

        if (!PhoneNumberUtil.getInstance().isPossibleNumber(phoneNumber, REGION_CODE))
            throw new IllegalArgumentException("The phone number \"" + phoneNumber + "\" contains invalid character " +
                    "or has an invalid format.");

        this.phoneNumber = phoneNumber;
    }

    protected PhoneNumber() {}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public int compareTo(PhoneNumber otherPhoneNumber) {
        return phoneNumber.compareTo(otherPhoneNumber.phoneNumber);
    }

    @Override
    public String toString() {
        return phoneNumber;
    }
}
