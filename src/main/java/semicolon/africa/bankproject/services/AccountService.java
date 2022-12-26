package semicolon.africa.bankproject.services;

import org.springframework.data.domain.Page;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Transactions;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.DepositFundResponse;
import semicolon.africa.bankproject.dto.response.WithdrawalFundResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountService {
   Account  openAccount(OpenAccountRequest openAccountRequest);

    Account findAccountById(String accountId);
    Page<Account> findAllAccount(FindAllAccountRequest findAllAccountRequest);
    void deleteAll();
    void deleteBYId(String accountId);
    Account updateAccount(UpdateAccountRequest updateAccountRequest);


    long totalNumberOfAccount();

BigDecimal depositFundsIntoAccount(DepositFundRequest depositFundRequest) throws Exception;


 Account findAccountByAccountName(String accountName);

 Account findAccountByAccountNames(String accountName);


 Account findAccountByAccountNUmber(String beneficiaryAccountNumber);
 BigDecimal WithdrawFundFromAccount(WithdrawalFundRequest withdrawalFundRequest);

 Transactions recordAccountTransaction(TransactionsRequest transactionsRequest);
}
