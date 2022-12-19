package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateAccountRequest {
    private String bankId;
    private String accountId;
    private String accountName;
    private String age;
    private String phoneNumber;
    private String email;
}
