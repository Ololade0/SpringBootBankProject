package semicolon.africa.bankproject.dto.response;

import lombok.*;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BankRegisterResponse {
    private String message;
    private String bankId;
    private Long customerId;
    private String bankLocation;
    private List<Customer> customerRegisterRequestList;

}
