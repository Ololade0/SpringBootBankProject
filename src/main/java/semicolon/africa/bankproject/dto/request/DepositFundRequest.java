package semicolon.africa.bankproject.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DepositFundRequest {
    private String bankId;
    private String customerId;
    private String accountId;
    private String accountNumber;
    private BigDecimal balance;
    private BigDecimal funds;
}
