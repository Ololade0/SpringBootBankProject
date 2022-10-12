package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeleteCustomerRequest {
    private Long customerId;
    private String customerName;
    private Long bankId;
}
