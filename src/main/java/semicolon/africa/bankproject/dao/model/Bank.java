package semicolon.africa.bankproject.dao.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Setter
@Getter

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bankLocation;
    private String bankName;
    private Long customerId;

   @OneToMany( fetch = FetchType.EAGER)
 //   @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private List<Customer> customers = new ArrayList<>();

}