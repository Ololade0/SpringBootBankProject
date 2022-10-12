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
@ToString
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bankLocation;
    private String bankName;


   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    private List<Customer> customers = new ArrayList<>();

}