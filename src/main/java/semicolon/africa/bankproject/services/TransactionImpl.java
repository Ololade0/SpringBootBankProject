package semicolon.africa.bankproject.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Transactions;
import semicolon.africa.bankproject.dao.repository.TransactionRepository;
import semicolon.africa.bankproject.dto.request.TransactionsRequest;

import java.util.List;

@Service
public class TransactionImpl implements TransactionServices {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transactions recordTransactions(TransactionsRequest transactionsRequest) {
        Transactions transactions = Transactions.builder()
                .sender("Olola")
                .benefactor("Adesuyi")
                .transactionAmount("900,000")
                .transactionType("Deposit")
                .build();
        return transactionRepository.save(transactions);

    }

    @Override
    public Transactions findTransactionById(String id) {
        return transactionRepository.findTransactionsById(id);
    }

    @Override
    public List<Transactions> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void deleteAll() {
        transactionRepository.deleteAll();

    }

    @Override
    public long size() {
        return transactionRepository.count();
    }

    @Override
    public void deleteById(String id) {
        transactionRepository.deleteById(id);

    }

}