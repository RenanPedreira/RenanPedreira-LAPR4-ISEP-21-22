package eapli.base.questionnairemanagement.dto;

import eapli.base.common.domain.model.Identifier;
import eapli.base.customermanagement.domain.dto.CustomerDTO;
import eapli.base.customermanagement.domain.dto.CustomerMapper;
import eapli.base.customermanagement.domain.model.Customer;
import eapli.base.questionnairemanagement.domain.TargetAudience;

import java.util.ArrayList;
import java.util.List;

public class TargetAudienceMapper {
    public static TargetAudienceDTO toDTO(String identifier,
                                          List<CustomerDTO> targetCustomer,
                                          List<CustomerDTO> respondingCustomer) {
        return new TargetAudienceDTO(identifier, targetCustomer, respondingCustomer);
    }

    public static TargetAudienceDTO toDTO(TargetAudience targetAudience) {
        List<CustomerDTO> targetCustomer = new ArrayList<>();
        List<CustomerDTO> respondingCustomer = new ArrayList<>();

        for (Customer customer : targetAudience.targetCustomers())
            targetCustomer.add(customer.toDTO());

        for (Customer customer : targetAudience.respondingCustomers())
            respondingCustomer.add(customer.toDTO());

        return new TargetAudienceDTO(targetAudience.identifier().getIdentifier(), targetCustomer, respondingCustomer);
    }

    public static TargetAudience toTargetAudience(TargetAudienceDTO targetAudienceDTO) {
        List<Customer> targetCustomers = new ArrayList<>();
        List<Customer> respondingCustomers = new ArrayList<>();

        for (CustomerDTO customerDTO : targetAudienceDTO.getTargetCustomers())
            targetCustomers.add(CustomerMapper.toCustomer(customerDTO));

        for (CustomerDTO customerDTO : targetAudienceDTO.getRespondingCustomers())
            respondingCustomers.add(CustomerMapper.toCustomer(customerDTO));

        return new TargetAudience(new Identifier(targetAudienceDTO.getIdentifier()), targetCustomers, respondingCustomers);
    }
}
