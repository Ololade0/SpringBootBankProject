package semicolon.africa.bankproject.dao.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document("Account")
public class Account {
    @Id
    private String id;
    private String accountName;
    private String accountNumber;
    private AccountType accountType;
    private String password;
    private String age;
    private String phoneNumber;
    private String email;
    private BigDecimal currentBalance;
    private BigDecimal funds;
    private String gender;
    @DBRef
    private List<Transactions> transactions = new ArrayList<>();

    public boolean passwordIsValid(String password) {
        this.password = password;

        return true;
    }

}
