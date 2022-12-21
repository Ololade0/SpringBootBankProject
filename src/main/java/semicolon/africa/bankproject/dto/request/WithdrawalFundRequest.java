package semicolon.africa.bankproject.dto.request;

import lombok.*;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class
WithdrawalFundRequest {
    private  String password;
    private Account account;
    private String accountNumber;
    private BigDecimal withdrawalAmount;


}
