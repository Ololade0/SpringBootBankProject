package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeleteAccountRequest {
    private String customerId;
    private String accountId;
    private String bankId;

}
