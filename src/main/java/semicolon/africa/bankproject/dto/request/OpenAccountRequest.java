package semicolon.africa.bankproject.dto.request;

import lombok.*;
import semicolon.africa.bankproject.dao.model.AccountType;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenAccountRequest {
    private String bankId;
    private String AccountName;
    private String accountNumber;
    private BigDecimal balance;
    private String password;
    private AccountType accountType;
    private String age;
    private String gender;
    private String email;
    private String phoneNumber;

}
