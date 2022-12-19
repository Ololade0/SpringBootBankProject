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
@Document("Bank")
public class Bank {
    @Id
    private String id;
    private String bankLocation;
    private String bankName;
    @DBRef
    private List<Customer> customers = new ArrayList<>();
    @DBRef
    private List<Account>accounts = new ArrayList<>();

}