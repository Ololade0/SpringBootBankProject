package semicolon.africa.bankproject.dao.model;

import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String customerName;
    private String customerAge;
    private String customerGender;
    @OneToMany(mappedBy = "customer")
    private List<Account> accounts = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "bank_id")
    private Bank bank;


}
