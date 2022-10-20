package semicolon.africa.bankproject.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.repository.AccountRepository;
import semicolon.africa.bankproject.dto.request.DepositFundRequest;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.request.UpdateAccountRequest;
import semicolon.africa.bankproject.dto.request.WithdrawalFundRequest;
import semicolon.africa.bankproject.exception.AccountAmountExceeded;
import semicolon.africa.bankproject.exception.AccountCannotBeFound;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account openAccount(OpenAccountRequest openAccountRequest) {
        Account newAccount = Account.builder()
                .email(openAccountRequest.getEmail())
                .phoneNumber(openAccountRequest.getPhoneNumber())
                .AccountName(openAccountRequest.getAccountName())
               // .beneficiaryAccountNumber(openAccountRequest.getAccountNumber())
                .beneficiaryAccountNumber(openAccountRequest.getAccountNumber())
                .age(openAccountRequest.getAge())
                .gender(openAccountRequest.getGender())
                .currentBalance(openAccountRequest.getBalance())
                .build();
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
        Account foundAccount = accountRepository.findAccountById(updateAccountRequest.getAccountId());
//        if (updateAccountRequest.getAccountName() != null) {
           // foundAccount.setAccountName(updateAccountRequest.getAccountName());
//        }
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
    public BigDecimal depositFundsIntoAccount(DepositFundRequest depositFundRequest) {
        Account newAccount = Account
                .builder()
                .currentBalance(depositFundRequest.getCurrentBalance())
                .senderAccountNumber(depositFundRequest.getSenderAccountNumber())
                .beneficiaryAccountNumber(depositFundRequest.getBeneficiaryAccount())
                .pin(depositFundRequest.getPin())
                .funds(depositFundRequest.getDepositFunds())
                .build();
        Account account = accountRepository.findAccountByBeneficiaryAccountNumber(depositFundRequest.getBeneficiaryAccount());
        if (account != null) ;
        return depositFundRequest.getCurrentBalance().add(depositFundRequest.getDepositFunds());

    }

    @Override
    public BigDecimal TransferFundsithValidPin(WithdrawalFundRequest withdrawalFundRequest) {
        Account newAccount = Account
                .builder()
                .currentBalance(withdrawalFundRequest.getCurrentBalance())
                .beneficiaryAccountNumber(withdrawalFundRequest.getBeneficiaryAccountNumber())
                .senderAccountNumber(withdrawalFundRequest.getSenderAccountNumber())
                .pin(withdrawalFundRequest.getPin())
                .funds(withdrawalFundRequest.getWithdrawalAmount())
                .build();
        Account account = accountRepository.findAccountByBeneficiaryAccountNumber(withdrawalFundRequest.getBeneficiaryAccountNumber());
        if (account != null) ;
        return withdrawalFundRequest.getCurrentBalance().subtract(withdrawalFundRequest.getWithdrawalAmount());
        //   if(withdrawalFundRequest.getWithdrawalAmount().compareTo(withdrawalFundRequest.getCurrentBalance()))
    }

}



