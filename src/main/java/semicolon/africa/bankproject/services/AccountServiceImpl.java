package semicolon.africa.bankproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.repository.AccountRepository;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.request.UpdateAccountRequest;

import java.util.List;


@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account openAccount(OpenAccountRequest openAccountRequest) {
        Account newAccount = Account.builder()
                .email(openAccountRequest.getEmail())
                .phoneNumber(openAccountRequest.getPhoneNumber())
                .AccountName(openAccountRequest.getAccountName())
                .age(openAccountRequest.getAge())
                .gender(openAccountRequest.getGender())

                .build();
       return accountRepository.save(newAccount);

    }

    @Override
    public Account findAccountById(Long accountId) {
        return accountRepository.findAccountById(accountId);
    }

    @Override
    public List<Account> findAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAll() {
        accountRepository.deleteAll();

    }

    @Override
    public void deleteBYId(Long accountId) {
        if(accountRepository.findById(accountId).isPresent() );
        accountRepository.deleteById(accountId);
    }

    @Override
    public Account updateAccount(UpdateAccountRequest updateAccountRequest) {
        Account foundAccount = accountRepository.findAccountById(updateAccountRequest.getId());

        if (updateAccountRequest.getAccountName() != null) {
            foundAccount.setAccountName(updateAccountRequest.getAccountName());
        }
        if (updateAccountRequest.getEmail() != null) {
            foundAccount.setEmail(updateAccountRequest.getEmail());
        }
        if (updateAccountRequest.getAge() != null) {
            foundAccount.setAge(updateAccountRequest.getAge());
        }
        if (updateAccountRequest.getPhoneNumber() != null) {
            foundAccount.setPhoneNumber(updateAccountRequest.getPhoneNumber());
        }
        accountRepository.save(foundAccount);
        return foundAccount;
    }

    @Override
    public long totalNumberOfAccount() {
        return accountRepository.count();
    }


}

