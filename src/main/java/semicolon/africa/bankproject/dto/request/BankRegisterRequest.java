package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankRegisterRequest {
    private long bankId;
    private String bankName;
    private String bankLOcation;
}
