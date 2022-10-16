package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteAllAccountRequest {
    private String accountId;
    private String customerId;
    private String bankId;
}
