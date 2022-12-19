package semicolon.africa.bankproject.services;
import org.springframework.data.domain.Page;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.DepositFundResponse;
import semicolon.africa.bankproject.dto.response.LoginResponse;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;
import semicolon.africa.bankproject.dto.response.WithdrawalFundResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer saveNewCustomer(CustomerRegisterRequest customerRegisterRequest);




    Optional<Customer> findById(String customerId);

    Customer findCustomerById(String customerId);

    Page<Customer> findAllCustomers(FindAllCustomerRequest findAllCustomerRequest);

    long totalNumberOfCustomer();

    void deleteAll();

    void deleteCustomer(String customerId);

    Customer updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest);

    void deleteCustomerById(String customerId);

    void deleteCustomersById(DeleteCustomerRequest deleteCustomerRequest);


    long totalNumberOfAccount();


//    Account findAccountById(FindAccountRequest findAccountRequest);

//    List<Account> findAllAccounts(FindAllAccountRequest findAllAccountRequest);


    LoginResponse login(LoginRest loginRest);

    Customer findCustomerByEmail(String customerEmail);




    ;
}