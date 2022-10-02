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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bankId;
    private String customerName;
    private String customerAge;
    private String customerGender;
    @OneToMany(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
private List<Account>accounts = new ArrayList<>();
}

