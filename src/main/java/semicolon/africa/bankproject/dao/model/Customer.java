package semicolon.africa.bankproject.dao.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document("Customer")
public class Customer {
    @Id
    private String customerId;
    private String customerEmail;
    private String customerAccountNumber;
    private String bankId;
    private String customerName;
    private String customerAge;
    private String customerGender;
    private String password;
    @DBRef
private List<Account>accounts = new ArrayList<>();


}

