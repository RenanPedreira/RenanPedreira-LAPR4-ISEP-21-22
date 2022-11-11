package eapli.base.customermanagement.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Data Structure which represents the Domain Concept "VAT Identification" (c.f. Order_System.png)
 *
 * @author Gonçalo Monteiro
 */
@Embeddable
public class VATIdentification implements ValueObject, Comparable<VATIdentification> {
    private static final String VAT_ID_REGEX = "((AT)?U[0-9]{8}|" +
            "(BE)?0[0-9]{9}|" +
            "(BG)?[0-9]{9,10}|" +
            "(CY)?[0-9]{8}L|" +
            "(CZ)?[0-9]{8,10}|(" +
            "DE)?[0-9]{9}|" +
            "(DK)?[0-9]{8}|" +
            "(EE)?[0-9]{9}|" +
            "(EL|GR)?[0-9]{9}|" +
            "(ES)?[0-9A-Z][0-9]{7}[0-9A-Z]|" +
            "(FI)?[0-9]{8}|" +
            "(FR)?[0-9A-Z]{2}[0-9]{9}|" +
            "(GB)?([0-9]{9}([0-9]{3})?|" +
            "[A-Z]{2}[0-9]{3})|" +
            "(HU)?[0-9]{8}|" +
            "(IE)?[0-9]S[0-9]{5}L|" +
            "(IT)?[0-9]{11}|" +
            "(LT)?([0-9]{9}|" +
            "[0-9]{12})|" +
            "(LU)?[0-9]{8}|" +
            "(LV)?[0-9]{11}|" +
            "(MT)?[0-9]{8}|" +
            "(NL)?[0-9]{9}B[0-9]{2}|" +
            "(PL)?[0-9]{10}|" +
            "(PT)?[0-9]{9}|" +
            "(RO)?[0-9]{2,10}|" +
            "(SE)?[0-9]{12}|" +
            "(SI)?[0-9]{8}|" +
            "(SK)?[0-9]{10})";

    @Column(name = "VAT_Identification")
    private String idVAT;

    public VATIdentification(String idVAT) throws IllegalArgumentException {
        Preconditions.nonNull(idVAT);

        if (!idVAT.matches(VAT_ID_REGEX))
            throw new IllegalArgumentException("The VAT Identification \"" + idVAT + "\" contains invalid " +
                    "characters or has an invalid format.");

        this.idVAT = idVAT;
    }

    protected VATIdentification() {}

    public String getVATIdentification() {
        return idVAT;
    }

    @Override
    public int compareTo(VATIdentification otherVATIdentification) {
        return idVAT.compareTo(otherVATIdentification.idVAT);
    }

    @Override
    public String toString() {
        return idVAT;
    }
}
