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
    private String beneficiaryAccount;
    private String senderAccountNumber;
    private int pin;
    private BigDecimal currentBalance;
    private BigDecimal depositFunds;
}
