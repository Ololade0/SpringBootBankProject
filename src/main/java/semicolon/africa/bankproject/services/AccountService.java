package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.request.UpdateAccountRequest;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;
import semicolon.africa.bankproject.dto.response.UpdateAccountResponse;

import java.util.List;
import java.util.Optional;

public interface AccountService {
   Account  openAccount(OpenAccountRequest openAccountRequest);
    Account findAccountById(String accountId);
    List<Account> findAllAccount();
    void deleteAll();
    void deleteBYId(String accountId);
    Account updateAccount(UpdateAccountRequest updateAccountRequest);


    long totalNumberOfAccount();
}
