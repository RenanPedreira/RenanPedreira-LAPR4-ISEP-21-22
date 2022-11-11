package eapli.base.questionnairemanagement.dto;

import eapli.base.customermanagement.domain.dto.CustomerDTO;

import java.util.List;

public class TargetAudienceDTO {
    private String identifier;
    private List<CustomerDTO> targetCustomers;
    private List<CustomerDTO> respondingCustomers;

    public TargetAudienceDTO(String identifier, List<CustomerDTO> targetCustomers, List<CustomerDTO> respondingCustomers) {
        this.identifier = identifier;
        this.targetCustomers = targetCustomers;
        this.respondingCustomers = respondingCustomers;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<CustomerDTO> getTargetCustomers() {
        return targetCustomers;
    }

    public List<CustomerDTO> getRespondingCustomers() {
        return respondingCustomers;
    }
}
