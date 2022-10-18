package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dto.request.DepositFundRequest;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.request.UpdateAccountRequest;
import semicolon.africa.bankproject.dto.request.WithdrawalFundRequest;
import semicolon.africa.bankproject.dto.response.DepositFundResponse;
import semicolon.africa.bankproject.dto.response.WithdrawalFundResponse;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
   Account  openAccount(OpenAccountRequest openAccountRequest);
    Account findAccountById(String accountId);
    List<Account> findAllAccount();
    void deleteAll();
    void deleteBYId(String accountId);
    Account updateAccount(UpdateAccountRequest updateAccountRequest);


    long totalNumberOfAccount();

BigDecimal depositFundsIntoAccount(DepositFundRequest depositFundRequest);
 WithdrawalFundResponse TransferFundsithValidPin(WithdrawalFundRequest withdrawalFundRequest);
}
