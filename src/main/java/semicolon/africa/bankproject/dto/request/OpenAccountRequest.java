package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenAccountRequest {
    private Long bankId;
    private String accountName;
    private String customerAge;
    private String customerGender;

}
