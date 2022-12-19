package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindAllAccountRequest {
   private int numberOfPages;
   private int pageNumber;
    private String bankId;
}
