package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Transactions;
import semicolon.africa.bankproject.dto.request.TransactionsRequest;

import java.util.List;


public interface TransactionServices {
    Transactions recordTransactions(TransactionsRequest transactionsRequest);

    Transactions findTransactionById(String id);

    List<Transactions> findAllTransactions();

    void deleteAll();

    long size();

    void deleteById(String id);
}