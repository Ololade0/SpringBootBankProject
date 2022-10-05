package semicolon.africa.bankproject.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomerRegisterResponse {
    private String message;
    private Long customerId;
}
