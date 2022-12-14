package semicolon.africa.bankproject.dto.request;

import lombok.*;
import semicolon.africa.bankproject.dao.model.Account;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRegisterRequest {
    private String bankId;
    private String customerEmail;
//    private String customerId;
    private String customerName;
    private String customerPassword;
    private String customerAccountNumber;
    private String customerAge;
    private String customerGender;
    private BankRegisterRequest bankRegisterRequest;
//    private List<OpenAccountRequest> openAccountRequests;



}
