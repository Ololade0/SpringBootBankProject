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
public class WithdrawalFundRequest {
    private String transactionId;
    private BigDecimal withdrawalAmount;
    private String accountNumber;
    private LocalDateTime transactionDate;
    private TransactionType transactionType;
    private BigDecimal currentBalance;
    private String pin;
   // private
}
