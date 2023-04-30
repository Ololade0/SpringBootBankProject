package semicolon.africa.bankproject.dao.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.model.Transactions;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transactions, String> {
    Transactions findTransactionsById(String transactionId);
    List<Transactions> findByCustomer(Account account);
}
