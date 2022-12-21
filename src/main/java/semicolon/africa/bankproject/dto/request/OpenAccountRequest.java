package semicolon.africa.bankproject.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenAccountRequest {
    private String bankId;
    private String AccountName;
    private String accountNumber;
    private BigDecimal balance;
    private String password;

    private String age;
    private String gender;
    private String email;
    private String phoneNumber;

}
