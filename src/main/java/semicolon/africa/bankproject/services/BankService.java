package semicolon.africa.bankproject.services;

import org.springframework.data.domain.Page;
import org.springframework.http.converter.HttpMessageNotReadableException;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.*;
import semicolon.africa.bankproject.exception.BankDoesNotExistException;

import java.util.List;
import java.util.Optional;

public interface BankService {
    BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest) throws BankDoesNotExistException;


    Bank findBankById(String bankId) throws BankDoesNotExistException;


    String deleteAll();

    long totalNumbersOfBanks();


    CustomerRegisterResponse saveCustomer(CustomerRegisterRequest customerRegisterRequest) throws HttpMessageNotReadableException;

    Page<Bank> findAllBanks(FindAllBankRequest findAllBankRequest);


    String deleteById(String bankId);

    UpdateBankResponse updateBankProfile(UpdateBankRequest updateBankRequest);

    long findTotalNumbersOfCustomers();

    Page<Customer> findAllCustomers(FindAllCustomerRequest findAllCustomerRequest);


    String deleteCustomerById(DeleteCustomerRequest deleteCustomerRequest);


    String deleteALLCustomers(DeleteAllCustomerRequest deleteAllCustomerRequest);

    Customer findCustomerId(FindCustomerRequest findCustomerRequest);

    UpdateCustomerProfileResponse updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest);

    void deletedAllCustomers();

    OpenAccountResponse openCustomerAccount(OpenAccountRequest openAccountRequest);

    long findTotalNumbersOfAccounts();


//    Account findAccountById(FindAccountRequest findAccountRequest);

    String deleteAllAccount();

//    List<Account> findAllAccounts(FindAllAccountRequest findAllAccountRequest);

    String deleteAccountById(DeleteAccountRequest deleteAccountRequest);

    UpdateAccountResponse updateAccountProfile(UpdateAccountRequest updateAccountRequest);

    Account findAccountByAccountName(String accountName);

    Account findAccountByAccountNUmber(String accountNumber);

    Account findAccountByAccountNames(String accountName);

    Account findAccountById(FindAccountRequest findAccountRequest);

    Page<Account> findAllAccounts(FindAllAccountRequest findAllAccountRequest);
}

