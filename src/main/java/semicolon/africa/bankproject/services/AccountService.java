package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Account;

import java.util.List;

public interface AccountService {
    Account saveNewAccount(Account account);

    List<Account> findAll();
}
