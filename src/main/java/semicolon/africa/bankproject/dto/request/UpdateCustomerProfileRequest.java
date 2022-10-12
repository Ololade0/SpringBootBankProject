package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerProfileRequest {
    private Long customerId;
    private Long bankId;
    private String customerName;
    private String customerAge;
    private String customerGender;
}
