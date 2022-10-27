package semicolon.africa.bankproject.services;

import org.springframework.http.converter.HttpMessageNotReadableException;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.*;

import java.util.List;

public interface BankService {
    BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest);


    Bank findBankById(String bankId);

    String deleteAll();

    long totalNumbersOfBanks();


    CustomerRegisterResponse saveCustomer(CustomerRegisterRequest customerRegisterRequest) throws HttpMessageNotReadableException;

    List<Bank> findAllBanks();


    String deleteById(String bankId);

    UpdateBankResponse updateBankProfile(UpdateBankRequest updateBankRequest);

    long findTotalNumbersOfCustomers();

    List<Customer> findAllCustomers(FindAllCustomerRequest findAllCustomerRequest);


    String deleteCustomerById(DeleteCustomerRequest deleteCustomerRequest);


    String deleteALLCustomers(DeleteAllCustomerRequest deleteAllCustomerRequest);

    Customer findCustomerId(FindCustomerRequest findCustomerRequest);

    UpdateCustomerProfileResponse updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest);

    void deletedAllCustomers();

    OpenAccountResponse openCustomerAccount(OpenAccountRequest openAccountRequest);

    long findTotalNumbersOfAccounts();


    Account findAccountById(FindAccountRequest findAccountRequest);

    String deleteAllAccount();

    List<Account> findAllAccounts(FindAllAccountRequest findAllAccountRequest);

    String deleteAccountById(DeleteAccountRequest deleteAccountRequest);

    UpdateAccountResponse updateAccountProfile(UpdateAccountRequest updateAccountRequest);

    Account findAccountByAccountName(String accountName);

    Account findAccountByAccountNUmber(String accountNumber);
}

