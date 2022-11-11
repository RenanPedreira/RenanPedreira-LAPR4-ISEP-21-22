package eapli.base.ordermanagement.dto;

import eapli.base.ordermanagement.domain.ClerkOrder;

public class ClerkOrderMapper {
    public ClerkOrderDTO toDTO(ClerkOrder order){
        System.out.println("physical");
        return new ClerkOrderDTO(order);

    }
}
