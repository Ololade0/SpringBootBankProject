package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.*;

import java.util.List;

public interface BankService {
    BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest);


    Bank getBankById(String bankId);

    void deleteAll();

    long totalNumbersOfBanks();


    CustomerRegisterResponse saveCustomer(CustomerRegisterRequest customerRegisterRequest);

    List<Bank> findAllBanks();


    void deleteById(String bankId);

    UpdateBankResponse updateBankProfile(UpdateBankRequest updateBankRequest);

    long findTotalNumbersOfCustomers();

    List<Customer> findAllCustomers(FindAllCustomerRequest findAllCustomerRequest);


    void deleteCustomerById(DeleteCustomerRequest deleteCustomerRequest);


    String deleteALLCustomers(DeleteAllCustomerRequest deleteAllCustomerRequest);

    Customer findCustomerId(FindBankRequest findBankRequest);

    UpdateCustomerProfileResponse updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest);

    void deletedAllCustomers();

    OpenAccountResponse openCustomerAccount(OpenAccountRequest openAccountRequest);

    long findTotalNumbersOfAccounts();


    Account findAccountById(FindAccountRequest findAccountRequest);

    void deleteAllAccount();

    List<Account> findAllAccounts(FindAllAccountRequest findAllAccountRequest);

    String deleteAccountById(DeleteAccountRequest deleteAccountRequest);

    UpdateAccountResponse updateAccountProfile(UpdateAccountRequest updateAccountRequest);
}

