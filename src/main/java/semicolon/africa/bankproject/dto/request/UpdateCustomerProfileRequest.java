package semicolon.africa.bankproject.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UpdateCustomerProfileRequest {
    private Long customerId;
    private String customerName;
    private String customerAge;
    private String customerGender;
}
