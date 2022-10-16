package semicolon.africa.bankproject.dao.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.bankproject.dao.model.Transactions;
@Repository
public interface TransactionRepository extends MongoRepository<Transactions, String> {
    Transactions findTransactionsById(String transactionId);
}
