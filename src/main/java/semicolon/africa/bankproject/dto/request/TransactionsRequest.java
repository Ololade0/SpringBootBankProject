package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
public class TransactionsRequest {
    private String transactionAmount;
    private String sender;
    private String benefactor;
    private String transactionType;

}
