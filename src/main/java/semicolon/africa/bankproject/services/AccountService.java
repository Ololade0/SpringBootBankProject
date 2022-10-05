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
    Account findAccountById(Long accountId);
    List<Account> findAllAccount();
    void deleteAll();
    void deleteBYId(Long accountId);
    Account updateAccount(UpdateAccountRequest updateAccountRequest);


    long totalNumberOfAccount();
}
