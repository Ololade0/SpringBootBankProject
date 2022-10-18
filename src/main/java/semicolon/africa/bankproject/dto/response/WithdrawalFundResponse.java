package semicolon.africa.bankproject.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class WithdrawalFundResponse {
    private String message;
    private BigDecimal currentBalance;
}
