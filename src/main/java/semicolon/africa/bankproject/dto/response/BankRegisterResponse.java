package semicolon.africa.bankproject.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankRegisterResponse {
    private String message;
    private Long bankId;
    private String bankLocation;
//    private Long customerId;
//    private String name;
}
