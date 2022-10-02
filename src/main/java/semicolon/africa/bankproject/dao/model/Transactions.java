package semicolon.africa.bankproject.dao.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Setter
@Getter

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal transactionAmount;
    private String sender;
    private String benefactor;
    private String transactionType;

}

