package semicolon.africa.bankproject.dao.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.bankproject.dao.model.Bank;

import java.util.Optional;

@Repository
public interface BankRepository extends MongoRepository<Bank, String> {
    Bank findBankById(String bankId);
    Optional<Bank>findById(String bankId);
}
