package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllTransaction {
    private int numberOfPerPages;
    private int pageNumber;

}
