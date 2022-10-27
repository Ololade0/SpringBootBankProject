package semicolon.africa.bankproject.dto.request;

import lombok.*;
import semicolon.africa.bankproject.dao.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class TransactionsRequest {
    private BigDecimal transactionAmount;
    private LocalDateTime transactionDate;
    private String pin;
    private String accountNumber;
    private TransactionType transactionType;


}
