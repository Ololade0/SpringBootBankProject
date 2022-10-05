package semicolon.africa.bankproject.dto.response;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpenAccountResponse {
    private String message;
    private Long id;

}
