package semicolon.africa.bankproject.dao.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String AccountName;
    private String age;
    private String gender;
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
