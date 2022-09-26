package semicolon.africa.bankproject.dto.request;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRegisterRequest {

    private Long bankId;
    private String bankName;
    private String customerName;
    private String customerAge;
    private String customerGender;

}
