package eapli.base.customermanagement.application;

import eapli.base.common.util.Triple;
import eapli.base.customermanagement.domain.dto.CustomerDTO;
import eapli.framework.application.UseCaseController;

import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author Gonçalo Monteiro
 */
@UseCaseController
public class RegisterCustomerController {
    RegisterCustomerService registrationService;

    public RegisterCustomerController() {
        registrationService = new RegisterCustomerService();
    }

    public CustomerDTO registerCustomer(String firstName,
                                        String lastName,
                                        String email,
                                        String phoneNumber,
                                        String idVAT,
                                        LocalDate birthdate,
                                        String gender,
                                        Map<Triple<String, String, Integer>, String> addressList)
    {
        return registrationService.buildCustomer(firstName, lastName, email, phoneNumber, idVAT, birthdate, gender, addressList);
    }

    public void persistCustomer(CustomerDTO customerDTO) {
        registrationService.persistsCustomer(customerDTO);
    }
}
