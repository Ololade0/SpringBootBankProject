package semicolon.africa.bankproject.dto.request;

import lombok.*;
import semicolon.africa.bankproject.dao.model.Customer;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BankRegisterRequest {
    private String bankId;
    private String bankName;
    private String banklocation;
   private List<Customer> customerRegisterRequestList;


}
