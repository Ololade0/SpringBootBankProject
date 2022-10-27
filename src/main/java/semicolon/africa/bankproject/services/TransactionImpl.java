package semicolon.africa.bankproject.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.TransactionType;
import semicolon.africa.bankproject.dao.model.Transactions;
import semicolon.africa.bankproject.dao.repository.TransactionRepository;
import semicolon.africa.bankproject.dto.request.DepositFundRequest;
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



    @Override
    public BigDecimal depositFunds(DepositFundRequest depositFundRequest) {
        Transactions newTransaction = Transactions.builder()
                .transactionDate(LocalDateTime.now())
                .currentBalance(depositFundRequest.getCurrentBalance())
                .transactionType(depositFundRequest.getTransactionType())
                .accountNumber(depositFundRequest.getBeneficiaryAccount())
                .transactionAmount(depositFundRequest.getTransactionAmount())
                .pin(depositFundRequest.getPin())
                .build();

            return depositFundRequest.getTransactionAmount().add(depositFundRequest.getCurrentBalance());






}

    @Override
    public BigDecimal TransferFund(WithdrawalFundRequest withdrawalFundRequest) {
        Transactions transactions = Transactions
                .builder()
                .transactionDate(LocalDateTime.now())
                .currentBalance(withdrawalFundRequest.getCurrentBalance())
                .pin(withdrawalFundRequest.getPin())
                .transactionAmount(withdrawalFundRequest.getWithdrawalAmount())
                .build();
            if(transactions.pinIsValid(withdrawalFundRequest.getPin())){
                BigDecimal funds = withdrawalFundRequest.getCurrentBalance().subtract(withdrawalFundRequest.getWithdrawalAmount());
                return funds;
            }
                throw new RuntimeException("Invalid Pin");

    }

}

