package semicolon.africa.bankproject.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import semicolon.africa.bankproject.dao.model.*;
import semicolon.africa.bankproject.dao.repository.TransactionRepository;

import semicolon.africa.bankproject.dto.request.FindAllTransaction;
import semicolon.africa.bankproject.dto.request.TransactionsRequest;



@Service

public class TransactionImpl implements TransactionServices {
    @Autowired
    private TransactionRepository transactionRepository;




    @Override
    public Transactions recordTransactions(TransactionsRequest transactionsRequest) {
        Transactions transactions = Transactions.builder()
                .accountNumber(transactionsRequest.getAccountNumber())
                .currentBalance(transactionsRequest.getCurrentBalance())
                .transactionAmount(transactionsRequest.getTransactionAmount())
                .transactionType(transactionsRequest.getTransactionType())
                .transactionDate(transactionsRequest.getTransactionDate())
                .pin(transactionsRequest.getPin())
                .build();
        return transactionRepository.save(transactions);


    }

    @Override
    public Transactions findTransactionById(String id) {
        return transactionRepository.findTransactionsById(id);
    }

    @Override
    public Page<Transactions> findAllTransactions(FindAllTransaction findAllTransaction) {
        Pageable pageable = PageRequest.of(findAllTransaction.getPageNumber() - 1, findAllTransaction.getNumberOfPerPages());
        return transactionRepository.findAll(pageable);
    }



    }












