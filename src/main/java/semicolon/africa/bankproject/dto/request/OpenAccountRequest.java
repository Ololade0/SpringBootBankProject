package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenAccountRequest {
    private Long bankId;
    private Long customerId;
    private String AccountName;
    private String customerAge;
    private String customerGender;

}
