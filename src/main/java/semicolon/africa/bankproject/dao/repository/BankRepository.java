package semicolon.africa.bankproject.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.bankproject.dao.model.Bank;

import java.util.Optional;


@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findBankByBankId(long id);




//    Bank findBankByBankId(Long id);
//    Bank findBankByBankName(String bankName);

}