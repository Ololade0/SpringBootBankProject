package semicolon.africa.bankproject.services;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.DepositFundResponse;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;
import semicolon.africa.bankproject.dto.response.WithdrawalFundResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer saveNewCustomer(CustomerRegisterRequest customerRegisterRequest);



    Optional<Customer> findById(String customerId);

    Customer findCustomerById(String customerId);

    List<Customer> findAllCustomers();

    long totalNumberOfCustomer();

    void deleteAll();

    void deleteCustomer(String customerId);

    Customer updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest);

    void deleteCustomerById(String customerId);

    void deleteCustomersById(DeleteCustomerRequest deleteCustomerRequest);

    OpenAccountResponse openAccount(OpenAccountRequest openAccountRequest);

    long totalNumberOfAccount();

    void deleteAllAccounts();

    String deleteAllAccount(DeleteAllAccountRequest deleteAllAccountRequest);


    Account findAccountById(FindAccountRequest findAccountRequest);

    List<Account> findAllAccounts(FindAllAccountRequest findAllAccountRequest);

    void deleteAccountById(DeleteAccountRequest deleteAccountRequest);

//     DepositFundResponse depositFunds(DepositFundRequest depositFundRequest);
//
//    WithdrawalFundResponse WithdrawFund(WithdrawalFundRequest withdrawalFundRequest);
//

    ;
}