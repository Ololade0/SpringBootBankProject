package semicolon.africa.bankproject.services;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Account;

import semicolon.africa.bankproject.dao.repository.AccountRepository;
import semicolon.africa.bankproject.dto.request.DepositFundRequest;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.request.UpdateAccountRequest;
import semicolon.africa.bankproject.dto.request.WithdrawalFundRequest;
import semicolon.africa.bankproject.utils.Utils;


import java.math.BigDecimal;
import java.util.List;


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
        Account newAccount = Account.builder()
                .email(openAccountRequest.getEmail())
                .phoneNumber(openAccountRequest.getPhoneNumber())
                .accountName(openAccountRequest.getAccountName())
                .beneficiaryAccountNumber(openAccountRequest.getAccountNumber())
                .age(openAccountRequest.getAge())
                .gender(openAccountRequest.getGender())
                .currentBalance(openAccountRequest.getBalance())
                .build();
        String customerAcctNum = utils.generateCustomerAccountNumber(10);
        newAccount.setBeneficiaryAccountNumber(customerAcctNum);
        return accountRepository.save(newAccount);

    }

    @Override
    public Account findAccountById(String accountId) {
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
    public void deleteBYId(String accountId) {
        if (accountRepository.findById(accountId).isPresent()) ;
        accountRepository.deleteById(accountId);
    }

    @Override
    public Account updateAccount(UpdateAccountRequest updateAccountRequest) {
        Account foundAccount = accountRepository.findAccountByBeneficiaryAccountNumber(updateAccountRequest.getAccountNumber());
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
    public BigDecimal depositFundsIntoAccount(DepositFundRequest depositFundRequest) throws Exception {
        Account foundAccount = accountRepository.findAccountByBeneficiaryAccountNumber(depositFundRequest.getBeneficiaryAccount());
        if (foundAccount != null) {
          return  transactionServices.depositFunds(depositFundRequest);
        }
       throw new RuntimeException("Transaction Unsuccessful");
    }


    @Override
    public Account findAccountByAccountName(String accountName) {
        return accountRepository.findAccountByAccountName(accountName);
    }

    @Override
    public Account findAccountByAccountNUmber(String beneficiaryAccountNumber) {
        return accountRepository.findAccountByBeneficiaryAccountNumber(beneficiaryAccountNumber);
    }

    @Override
    public BigDecimal WithdrawFundFromAccount(WithdrawalFundRequest withdrawalFundRequest) {
        Account foundAccount = accountRepository.findAccountBySenderAccountNumber(withdrawalFundRequest.getAccountNumber());
        if (foundAccount != null) {
            return  transactionServices.TransferFund(withdrawalFundRequest);
        }
        throw new RuntimeException("Transaction Unsuccessful");

    }
}




