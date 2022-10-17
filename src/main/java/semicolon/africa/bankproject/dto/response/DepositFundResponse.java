package semicolon.africa.bankproject.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DepositFundResponse {
    private String message;
    private BigDecimal balance;
    private String transactionId;
}
