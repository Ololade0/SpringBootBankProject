package semicolon.africa.bankproject.dao.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Transaction")
public class Transactions {
    @Id
    private String id;
    private String transactionAmount;
    private String sender;
    private String benefactor;
    private String transactionType;

}

