package semicolon.africa.bankproject.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Account;

import semicolon.africa.bankproject.dao.repository.AccountRepository;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.utils.Utils;


import java.math.BigDecimal;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionServices transactionServices;
    @Autowired
    private Utils utils;

    @Override
    public Account openAccount(OpenAccountRequest openAccountRequest) {

        Account newAccount =  Account.builder()
                .email(openAccountRequest.getEmail())
                .phoneNumber(openAccountRequest.getPhoneNumber())
                .accountName(openAccountRequest.getAccountName())
                .accountNumber(openAccountRequest.getAccountNumber())
                .age(openAccountRequest.getAge())
                .gender(openAccountRequest.getGender())
                .currentBalance(openAccountRequest.getBalance())
                .build();
        String customerAcctNum = utils.generateCustomerAccountNumber(10);;
        openAccountRequest.setAccountNumber(customerAcctNum);
        return accountRepository.save(newAccount);

    }

    @Override
    public Account findAccountById(String accountId) {
        return accountRepository.findAccountById(accountId);
    }

    @Override
    public Page<Account> findAllAccount(FindAllAccountRequest findAllAccountRequest) {
        Pageable pageable = PageRequest.of(findAllAccountRequest.getPageNumber()-1, findAllAccountRequest.getNumberOfPages());
        return accountRepository.findAll(pageable);
    }

    @Override
    public void deleteAll() {
        accountRepository.deleteAll();

    }

    @Override
    public void deleteBYId(String accountId) {
        if (accountRepository.findById(accountId).isPresent()) ;
        accountRepository.deleteById(accountId);
    }

    @Override
    public Account updateAccount(UpdateAccountRequest updateAccountRequest) {
        Account foundAccount = accountRepository.findAccountById(updateAccountRequest.getAccountId());
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


    @Override
    public Account findAccountByAccountName(String accountName) {
        return accountRepository.findAccountByAccountName(accountName);
    }

    @Override
    public Account findAccountByAccountNames(String accountName) {
        return accountRepository.findFirstByAccountName(accountName);
    }


    @Override
    public Account findAccountByAccountNUmber(String beneficiaryAccountNumber) {
        return accountRepository.findAccountByAccountNumber(beneficiaryAccountNumber);
    }


    @Override
    public BigDecimal depositFundsIntoAccount(DepositFundRequest depositFundRequest) throws Exception {
        Account foundAccount = accountRepository.findAccountByAccountNumber(depositFundRequest.getBeneficiaryAccount());
        if ((foundAccount!= null)) {
            foundAccount.setCurrentBalance(foundAccount.getCurrentBalance().add(depositFundRequest.getTransactionAmount()));
            accountRepository.save(foundAccount);
            return foundAccount.getCurrentBalance();

        }
        throw new RuntimeException("Transaction unsuccessful");
//
    }


    @Override
    public BigDecimal WithdrawFundFromAccount(WithdrawalFundRequest withdrawalFundRequest) {
        Account foundAccount = accountRepository.findAccountByAccountNumber(withdrawalFundRequest.getAccountNumber());
            foundAccount.setCurrentBalance(foundAccount.getCurrentBalance().subtract(withdrawalFundRequest.getWithdrawalAmount()));
             accountRepository.save(foundAccount);
            return foundAccount.getCurrentBalance();
       }


    }









