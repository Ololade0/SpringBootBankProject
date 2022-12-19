package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllBankRequest {
    private int numberOfPages;
    private int pageNumber;
}
