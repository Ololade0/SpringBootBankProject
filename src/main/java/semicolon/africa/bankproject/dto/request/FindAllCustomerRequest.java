package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FindAllCustomerRequest {
    private Long customerId;
    private Long bankId;
    private String customerName;
}
