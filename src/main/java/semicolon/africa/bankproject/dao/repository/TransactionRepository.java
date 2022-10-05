package semicolon.africa.bankproject.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import semicolon.africa.bankproject.dao.model.Transactions;
@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    Transactions findTransactionsById(Long transactionId);
}
