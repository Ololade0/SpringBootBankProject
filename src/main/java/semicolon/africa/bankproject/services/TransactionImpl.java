package semicolon.africa.bankproject.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.TransactionType;
import semicolon.africa.bankproject.dao.model.Transactions;
import semicolon.africa.bankproject.dao.repository.TransactionRepository;
import semicolon.africa.bankproject.dto.request.DepositFundRequest;
import semicolon.africa.bankproject.dto.request.FindAllTransaction;
import semicolon.africa.bankproject.dto.request.TransactionsRequest;
import semicolon.africa.bankproject.dto.request.WithdrawalFundRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service

public class TransactionImpl implements TransactionServices {
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public Transactions recordTransactions(TransactionsRequest transactionsRequest) {
        Transactions transactions = Transactions.builder()
                .accountNumber(transactionsRequest.getAccountNumber())
//                .currentBalance(transactionsRequest.getCurrentBalance())
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
        Pageable pageable = PageRequest.of(findAllTransaction.getPageNumber()-1, findAllTransaction.getNumberOfPerPages());
        Page<Transactions> transactionsPage = transactionRepository.findAll(pageable);
        return transactionsPage;
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

        @Override
    public BigDecimal depositFunds(DepositFundRequest depositFundRequest) throws Exception {
        Transactions transactions = new Transactions();
//       transactions.setCurrentBalance(BigDecimal.valueOf(10000));
        transactions.setTransactionAmount(depositFundRequest.getTransactionAmount());
        transactions.setAccountNumber(depositFundRequest.getBeneficiaryAccount());
        transactions.setCurrentBalance(transactions.getCurrentBalance().add(depositFundRequest.getTransactionAmount()));
        transactionRepository.save(transactions);
        return transactions.getCurrentBalance();

//        depositFundRequest.getTransactionAmount().add(depositFundRequest.)

    }






//    @Override
//    public BigDecimal TransferFund(WithdrawalFundRequest withdrawalFundRequest) {
//        Transactions transactions = Transactions
//                .builder()
//                .transactionDate(LocalDateTime.now())
//                .currentBalance(withdrawalFundRequest.getCurrentBalance())
//                .pin(withdrawalFundRequest.getPin())
//                .transactionAmount(withdrawalFundRequest.getWithdrawalAmount())
//                .build();
//            if(transactions.pinIsValid(withdrawalFundRequest.getPin())){
//                BigDecimal funds = withdrawalFundRequest.getCurrentBalance().subtract(withdrawalFundRequest.getWithdrawalAmount());
//                return funds;
//            }
//                throw new RuntimeException("Invalid Pin");
//
//    }
 }



