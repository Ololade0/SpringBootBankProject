package semicolon.africa.bankproject.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindBankRequest {
    private String bankId;
    private String customerId;
}
