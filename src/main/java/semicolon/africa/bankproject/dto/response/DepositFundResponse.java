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
//   public BigDecimal bigDecimal;
    private String message;
    private String currentBalance;
    private String id;
}
