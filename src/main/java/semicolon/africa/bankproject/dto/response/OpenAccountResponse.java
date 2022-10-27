package semicolon.africa.bankproject.dto.response;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OpenAccountResponse {
    private String accountName;
    private String accountNumber;
    private String message;
    private String id;



//    public String getAccountNumber() {
//    }
}
