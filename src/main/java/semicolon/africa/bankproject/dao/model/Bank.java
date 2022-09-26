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

public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bankId;
    private String bankName;
    private String bankLocation;

    @OneToMany(mappedBy = "bank", fetch = FetchType.EAGER)
    private List<Customer> customers = new ArrayList<>();


}
