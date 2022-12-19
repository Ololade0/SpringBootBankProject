package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FindAllCustomerRequest {
    private int numberOfPages;
    private int pageNumber;
   // private String customerId;
    private String bankId;
   // private String customerName;
}
