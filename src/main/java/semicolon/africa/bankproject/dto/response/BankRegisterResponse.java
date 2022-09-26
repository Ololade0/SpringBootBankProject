package semicolon.africa.bankproject.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankRegisterResponse {
    private String message;
    private  Long id;
}
