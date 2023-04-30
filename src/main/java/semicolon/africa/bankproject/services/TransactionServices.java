package semicolon.africa.bankproject.services;

import org.springframework.data.domain.Page;

import semicolon.africa.bankproject.dao.model.Transactions;

import semicolon.africa.bankproject.dto.request.FindAllTransaction;
import semicolon.africa.bankproject.dto.request.TransactionsRequest;

public interface TransactionServices {
//
Transactions recordTransactions(TransactionsRequest transactionsRequest);

    Transactions findTransactionById(String id);

    Page<Transactions> findAllTransactions(FindAllTransaction findAllTransaction);

}
