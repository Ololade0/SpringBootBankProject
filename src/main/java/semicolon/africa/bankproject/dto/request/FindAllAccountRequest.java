package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllAccountRequest {
    private String accountName;
    private String bankId;
    private String customerId;
    private String accountId;
}
