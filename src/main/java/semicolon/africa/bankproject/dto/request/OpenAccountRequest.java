package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenAccountRequest {
    private Long bankId;

    private String AccountName;
    private String age;
    private String gender;
    private String email;
    private String phoneNumber;

}
