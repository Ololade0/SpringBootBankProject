package semicolon.africa.bankproject.dao.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.bankproject.dao.model.Account;
@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findAccountById(String id);
    Account findAccountByBeneficiaryAccountNumber(String accountNumber);
    Account findAccountByAccountName(String accountName);
    Account findAccountBySenderAccountNumber(String accountNumber);
}
