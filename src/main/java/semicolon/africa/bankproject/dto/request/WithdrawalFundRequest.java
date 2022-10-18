package semicolon.africa.bankproject.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WithdrawalFundRequest {
    private String beneficiaryAccountNumber;
    private BigDecimal withdrawalAmount;
    private String senderAccountNumber;
    private BigDecimal currentBalance;
    private int pin;
   // private
}
