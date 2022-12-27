package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAccountBYAccountNUmber {
    private String bankId;
    private String accountNumber;
}
