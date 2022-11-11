package eapli.base.common.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Comparator;

/**
 * Data Structure which represents the Domain Concept "Address" (c.f. User_Management_DM.png).
 * Note: Only identifies and validates addresses following the Portuguese format.
 *
 * @author Gonçalo Monteiro & Renan Oliveira
 */
@Embeddable
public class Address implements ValueObject, Comparable<Address> {
    private enum AddressType {
        BILLING_ADDRESS,
        DELIVERY_ADDRESS;

        public static boolean contains(String value) {
            for (AddressType addressType : AddressType.values()) {
                if (addressType.name().equals(value))
                    return true;
            }

            return false;
        }
    }

    private static final String STREET_NAME_REGEX = "[A-Z][a-z]*( [a-z]+)?([' '\']?([A-Z][a-z]+ )+([a-z]+ )?|[' '\']?[A-Z][a-z]+)+";
    private static final String CITY_NAME_REGEX = "([A-Z][a-z]*( [a-z]+)?([' ]?([A-Z][a-z]+ ?)+([a-z]+ )?|[' ][A-Z][a-z]+)+|[A-Z][a-z]+)";
    private static final int MINIMAL_DOOR_NUMBER = 0;

    @Column(name = "AddressType")
    private String addressType;
    @Column(name = "StreetName")
    private String streetName;
    @Column(name = "CityName")
    private String cityName;
    @Column(name = "DoorNumber")
    private int doorNumber;

    public Address(String addressType ,String streetName, String cityName, int doorNumber) throws IllegalArgumentException {
        Preconditions.noneNull(addressType, streetName, cityName, doorNumber);

        addressType = addressType.toUpperCase();
        addressType = addressType.replace(" ", "_");

        if (!AddressType.contains(addressType))
            throw new IllegalArgumentException("The address type \"" + addressType + "\" isn't a valid address type.");
        else if (!streetName.matches(STREET_NAME_REGEX))
            throw new IllegalArgumentException("The street name \"" + streetName + "\" contains invalid character " +
                    "or has an invalid format.");
        else if (!cityName.matches(CITY_NAME_REGEX))
            throw new IllegalArgumentException("The city name \"" + cityName + "\" contains invalid character " +
                    "or has an invalid format.");
        else if (doorNumber < MINIMAL_DOOR_NUMBER)
            throw new IllegalArgumentException("The door number \"" + streetName + "\" has an invalid format.");

        this.addressType = addressType;
        this.streetName = streetName;
        this.cityName = cityName;
        this.doorNumber = doorNumber;
    }

    protected Address() {}

    @Override
    public int compareTo(Address otherAddress) {
        return Comparator.comparing(Address::getAddressType)
                .thenComparing(Address::getStreetName)
                .thenComparing(Address::getCityName)
                .thenComparing(Address::getDoorNumber)
                .compare(this, otherAddress);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(addressType).append("\n")
                .append(streetName).append(", ")
                .append(doorNumber).append("\n")
                .append(cityName);

        return builder.toString();
    }

    public String getAddressType() {
        return addressType;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public int getDoorNumber() {
        return doorNumber;
    }
}