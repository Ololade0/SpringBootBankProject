package semicolon.africa.bankproject.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BankRegisterResponse {
    private String message;
    private String bankId;
    private Long customerId;
    private String bankLocation;

}
