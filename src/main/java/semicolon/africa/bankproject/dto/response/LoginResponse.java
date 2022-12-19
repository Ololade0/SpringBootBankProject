package semicolon.africa.bankproject.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginResponse {
    private int code;
    private String message;

}
