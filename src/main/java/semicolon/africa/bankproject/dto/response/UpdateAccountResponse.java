package semicolon.africa.bankproject.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UpdateAccountResponse {
    private String accountName;
    private String message;
}
