package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomerRegisterRequest {
    private Long bankId;
    private String bankLocation;
    private String customerName;
    private String customerAge;
    private String customerGender;


}
