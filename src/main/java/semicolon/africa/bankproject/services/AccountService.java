package semicolon.africa.bankproject.services;

import org.springframework.data.domain.Page;
import semicolon.africa.bankproject.dao.model.Account;

import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.*;


import java.math.BigDecimal;


public interface AccountService {
   Account  openAccount(OpenAccountRequest openAccountRequest);

    Account findAccountById(String accountId);
    Page<Account> findAllAccount(FindAllAccountRequest findAllAccountRequest);
    void deleteAll();
    void deleteBYId(String accountId);
    Account updateAccount(UpdateAccountRequest updateAccountRequest);


    long totalNumberOfAccount();

BigDecimal depositFundsIntoAccount(DepositFundRequest depositFundRequest);



 Account findAccountByAccountName(String accountName);

 Account findAccountByAccountNames(String accountName);

  Account findAccountByAccountNUmber(String beneficiaryAccountNumber);
 BigDecimal WithdrawFundFromAccount(WithdrawalFundRequest withdrawalFundRequest);

}
