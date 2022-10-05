package semicolon.africa.bankproject.dao.model;

import lombok.*;

//import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String AccountName;
    private String age;
    private String phoneNumber;
    private String email;
    private String gender;

@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transactions> transactions = new ArrayList<>();



}
