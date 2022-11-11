package eapli.base.customermanagement.domain.dto;

import eapli.base.common.util.Triple;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.representations.dto.DTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * TODO - Class description;
 *
 * @author Gonçalo Monteiro
 */
@DTO
public class CustomerDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<String> roleSet;
    private String identifier;

    private LocalDate birthdate;
    private String gender;
    private String phoneNumber;
    private String idVAT;
    private Map<Triple<String, String, Integer>, String> addressList;
    private HistoryDTO historyDTO;

    public CustomerDTO(String userName,
                       String firstName,
                       String lastName,
                       String email,
                       String password,
                       Set<String> roleSet,
                       String identifier,
                       LocalDate birthdate,
                       String gender,
                       String phoneNumber,
                       String idVAT,
                       Map<Triple<String, String, Integer>, String> addressList,
                       HistoryDTO historyDTO)
    {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roleSet = roleSet;
        this.identifier = identifier;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.idVAT = idVAT;
        this.addressList = addressList;
        this.historyDTO = historyDTO;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoleSet() {
        return roleSet;
    }

    public String getIdentifier() {
        return identifier;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIdVAT() {
        return idVAT;
    }

    public Map<Triple<String, String, Integer>, String> getAddressList() {
        return addressList;
    }

    public HistoryDTO getHistory() {
        return historyDTO;
    }
}
