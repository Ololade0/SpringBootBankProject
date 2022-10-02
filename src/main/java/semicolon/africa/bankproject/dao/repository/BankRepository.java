package semicolon.africa.bankproject.dao.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.bankproject.dao.model.Bank;
@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findBankById(long bankId);
}
