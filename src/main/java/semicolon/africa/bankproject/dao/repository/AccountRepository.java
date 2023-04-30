package semicolon.africa.bankproject.dao.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.bankproject.dao.model.Account;
@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findAccountById(String id);
    Account findAccountByAccountNumber(String accountNumber);
    Account findByAccountNumber(String accountNumber, String userId);
    Account findAccountByAccountName(String accountName);
    Account findFirstByAccountName(String accountName);
}
