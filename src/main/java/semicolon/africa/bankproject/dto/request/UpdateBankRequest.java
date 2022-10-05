package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBankRequest {
    private Long bankId;
    private String bankLocation;
    private String bankName;

}
