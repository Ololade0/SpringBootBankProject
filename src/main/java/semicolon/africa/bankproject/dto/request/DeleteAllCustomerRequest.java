package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeleteAllCustomerRequest {
    private Long customerId;
    private String customerName;
    private Long bankId;
}
