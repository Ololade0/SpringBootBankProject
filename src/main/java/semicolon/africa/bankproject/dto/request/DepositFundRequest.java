package semicolon.africa.bankproject.dto.request;

import lombok.*;
import semicolon.africa.bankproject.dao.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DepositFundRequest {
    private LocalDateTime transactionDate;
    private String beneficiaryAccount;
    private String pin;
    private BigDecimal currentBalance;
    private TransactionType transactionType;
    private BigDecimal transactionAmount;


}
