package semicolon.africa.bankproject.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindCustomerRequest {
    private String bankId;
    private String customerId;
}
