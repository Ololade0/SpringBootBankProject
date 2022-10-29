package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BankRegisterRequest {
    private String bankId;
    private String bankName;
    private String banklocation;
}
