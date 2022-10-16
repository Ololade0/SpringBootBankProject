package semicolon.africa.bankproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.repository.CustomerRepository;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;
import semicolon.africa.bankproject.exception.AccountCannotBeFound;
import semicolon.africa.bankproject.exception.BankDoesNotExistException;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service


public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountService accountService;

    @Override
    public Customer saveNewCustomer(CustomerRegisterRequest customerRegister) {
        Customer newCustomer = Customer.builder().
                customerName(customerRegister.getCustomerName())
                .customerGender(customerRegister.getCustomerGender())
                .customerAge(customerRegister.getCustomerAge())
                .build();
        return customerRepository.save(newCustomer);

    }


    @Override
    public Optional<Customer> findById(String customerId) {
        return Optional.ofNullable(customerRepository.findCustomerByCustomerId(customerId));
    }

    @Override
    public Customer findCustomerById(String customerId) {
        return customerRepository.findCustomerByCustomerId(customerId);

    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public long totalNumberOfCustomer() {
        return customerRepository.count();
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();

    }

    @Override
    public void deleteCustomer(String customerId) {
        if (customerRepository.findById(customerId).isPresent()) {
            customerRepository.deleteById(customerId);
        }


    }

    @Override
    public Customer updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest) {
        Customer foundCustomer = customerRepository.findCustomerByCustomerId(updateCustomerProfileRequest.getCustomerId());
        if (updateCustomerProfileRequest.getCustomerName() != null) {
            foundCustomer.setCustomerName(updateCustomerProfileRequest.getCustomerName());
        }

        if (updateCustomerProfileRequest.getCustomerAge() != null) {
            foundCustomer.setCustomerAge(updateCustomerProfileRequest.getCustomerAge());
        }

        if (updateCustomerProfileRequest.getCustomerGender() != null) {
            foundCustomer.setCustomerGender(updateCustomerProfileRequest.getCustomerGender());
        }

        return customerRepository.save(foundCustomer);
    }

    @Override
    public void deleteCustomerById(String customerId) {
        customerRepository.deleteById(customerId);

    }

    @Override
    public void deleteCustomersById(DeleteCustomerRequest deleteCustomerRequest) {
        customerRepository.deleteById(deleteCustomerRequest.getCustomerId());

    }

    @Override
    public OpenAccountResponse openAccount(OpenAccountRequest openAccountRequest) {
        Account foundAccount = accountService.openAccount(openAccountRequest);
        Customer foundCustomer = customerRepository.findCustomerByCustomerId(openAccountRequest.getCustomerId());
        if (foundCustomer != null) {
            foundCustomer.getAccounts().add(foundAccount);
            customerRepository.save(foundCustomer);
        }
        return OpenAccountResponse.builder()
                .message("Account successfully opened")
                .id(foundAccount.getId())
                .build();

    }

    @Override
    public long totalNumberOfAccount() {
        return accountService.totalNumberOfAccount();
    }

    @Override
    public void deleteAllAccounts() {
        accountService.deleteAll();

    }

    @Override
    public String deleteAllAccount(DeleteAllAccountRequest deleteAllAccountRequest) {
        Customer foundCustomer = customerRepository.findCustomerByCustomerId(deleteAllAccountRequest.getCustomerId());
        if (foundCustomer != null) {
            accountService.deleteAll();
            return "Account successfully deleted";
        }
        return "error";
    }

    @Override
    public Account findAccountById(FindAccountRequest findAccountRequest) {
        Customer customer = customerRepository.findCustomerByCustomerId(findAccountRequest.getCustomerId());
        if (customer != null) {
            return accountService.findAccountById(findAccountRequest.getAccoundId());
        }
        throw new AccountCannotBeFound("Account does not exist");
    }

    @Override
    public List<Account> findAllAccounts(FindAllAccountRequest findAllAccountRequest) {
        Customer foundCustomer = customerRepository.findCustomerByCustomerId(findAllAccountRequest.getCustomerId());
        if (foundCustomer != null) {
            return accountService.findAllAccount();
        }
        throw new AccountCannotBeFound("Accounts does not exist");
    }

    @Override
    public void deleteAccountById(DeleteAccountRequest deleteAccountRequest) {
        Customer foundCustomer = customerRepository.findCustomerByCustomerId(deleteAccountRequest.getCustomerId());
        if (foundCustomer != null) {
            List<Account> accounts = foundCustomer.getAccounts();
            for (int i = 0; i < accounts.size(); i++) {
                if(accounts.get(i).getId().equalsIgnoreCase(deleteAccountRequest.getAccountId())){
                    accountService.deleteBYId(deleteAccountRequest.getAccountId());
                    accounts.remove(accounts.get(i));
                    customerRepository.save(foundCustomer);
                }

            }
        }

    }

}