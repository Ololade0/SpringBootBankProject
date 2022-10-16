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
@Document("Account")
public class Account {
    @Id
    private String id;
    private String AccountName;
    private String age;
    private String phoneNumber;
    private String email;
    private String gender;
    @DBRef
    private List<Transactions> transactions = new ArrayList<>();



}
