package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Transactions;
import semicolon.africa.bankproject.dto.request.DepositFundRequest;
import semicolon.africa.bankproject.dto.request.TransactionsRequest;
import semicolon.africa.bankproject.dto.request.WithdrawalFundRequest;

import java.math.BigDecimal;
import java.util.List;


public interface TransactionServices {
    Transactions recordTransactions(TransactionsRequest transactionsRequest);

    Transactions findTransactionById(String id);

    List<Transactions> findAllTransactions();

    void deleteAll();

    long size();

    void deleteById(String id);


  BigDecimal depositFunds(DepositFundRequest depositFundRequest) throws Exception;

    BigDecimal TransferFund(WithdrawalFundRequest withdrawalFundRequest);
}
