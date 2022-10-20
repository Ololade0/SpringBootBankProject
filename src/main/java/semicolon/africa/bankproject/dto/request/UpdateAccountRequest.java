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
    private String customerId;
    private String accountId;
//    private String AccountName;
    private String age;
    private String phoneNumber;
    private String email;
}
