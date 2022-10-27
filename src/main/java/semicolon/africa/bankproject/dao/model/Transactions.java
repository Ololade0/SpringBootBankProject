package semicolon.africa.bankproject.dao.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document("Transaction")
public class Transactions {
    @Id
    private String id;
    private String pin;
    private LocalDateTime transactionDate;
    private BigDecimal currentBalance;
    private BigDecimal transactionAmount;
    private String accountNumber;
    private TransactionType transactionType;

    public  boolean pinIsValid(String pin){
        return this.pin.equals(pin);

    }

}

