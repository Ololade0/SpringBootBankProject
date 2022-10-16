package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeleteAllCustomerRequest {
    private String customerId;
    private String customerName;
    private String bankId;
}
