package semicolon.africa.bankproject.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRegisterRequest {
    private String bankId;
    private String customerEmail;
//    private String customerId;
    private String customerName;
    private String customerPassword;
    private String customerAccountNumber;
    private String customerAge;
    private String customerGender;



}
