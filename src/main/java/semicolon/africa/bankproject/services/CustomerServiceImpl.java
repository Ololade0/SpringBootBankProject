package semicolon.africa.bankproject.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Account;

import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.repository.CustomerRepository;
import semicolon.africa.bankproject.dto.request.*;

import semicolon.africa.bankproject.dto.response.DepositFundResponse;
import semicolon.africa.bankproject.dto.response.LoginResponse;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;
import semicolon.africa.bankproject.dto.response.WithdrawalFundResponse;
import semicolon.africa.bankproject.exception.AccountCannotBeFound;
import semicolon.africa.bankproject.exception.CustomerCannotBeFound;
import semicolon.africa.bankproject.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

@Service


public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountService accountService;


//    @Autowired
//    Utils utils;




    @Override
    public Customer saveNewCustomer(Customer customerRegister) {
        List<Account> accountList = new ArrayList<>();
        for (int i = 0; i < customerRegister.getAccounts().size(); i++) {
            OpenAccountRequest account = new OpenAccountRequest();
//            Account account = new Account();
//            account.setId(String.valueOf(i));
            account.setPassword(customerRegister.getAccounts().get(i).getPassword());
            account.setAccountName(customerRegister.getAccounts().get(i).getAccountName());
            account.setAccountNumber(customerRegister.getAccounts().get(i).getAccountNumber());
//            account.setCurrentBalance(customerRegister.getAccounts().get(i).getCurrentBalance());
            account.setEmail(customerRegister.getAccounts().get(i).getEmail());
            account.setPhoneNumber(customerRegister.getAccounts().get(i).getPhoneNumber());
            account.setAccountType(customerRegister.getAccounts().get(i).getAccountType());
//            account.setTransactions(customerRegister.getAccounts().get(i).getTransactions());
            account.setAge(customerRegister.getAccounts().get(i).getAge());
//            account.setId(customerRegister.getAccounts().get(i).getId());
            Account account1 = accountService.openAccount(account);
            accountList.add(account1);

        }

        ModelMapper modelMapper = new ModelMapper();
     Customer customer =    modelMapper.map(customerRegister, Customer.class);
           customer.setAccounts(accountList);
        return customerRepository.save(customer);

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
    public Page<Customer> findAllCustomers(FindAllCustomerRequest findAllCustomerRequest) {
        Pageable pageable = PageRequest.of(findAllCustomerRequest.getPageNumber()-1, findAllCustomerRequest.getNumberOfPages());
        return customerRepository.findAll(pageable);

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
    public long totalNumberOfAccount() {
        return accountService.totalNumberOfAccount();
    }




//    @Override
//    public Account findAccountById(FindAccountRequest findAccountRequest) {
//        Customer customer = customerRepository.findCustomerByCustomerId(findAccountRequest.getCustomerId());
//        if (customer != null) {
//            return accountService.findAccountById(findAccountRequest.getAccoundId());
//        }
//        throw new AccountCannotBeFound("Account does not exist");
//    }

//    @Override
//    public List<Account> findAllAccounts(FindAllAccountRequest findAllAccountRequest) {
//        Customer foundCustomer = customerRepository.findCustomerByCustomerId(findAllAccountRequest.getCustomerId());
//        if (foundCustomer != null) {
//            return accountService.findAllAccount();
//        }
//        throw new AccountCannotBeFound("Accounts does not exist");
//    }


    @Override
    public Customer findCustomerByEmail(String customerEmail) {
        Optional<Customer> foundCustomer = customerRepository.findCustomerByCustomerEmail(customerEmail);
        if (foundCustomer.isEmpty()) {
            throw new CustomerCannotBeFound("Customer cannot be found");
        }
        return foundCustomer.get();

    }

    @Override
    public LoginResponse login(LoginRest loginRest) {
        var foundCustomer = customerRepository.findCustomerByCustomerEmail(loginRest.getEmail());
//        if (foundCustomer.isPresent() && foundCustomer.get().getPassword().equals(loginRest.getPassword()))
       return buildSuccessfulLoginResponse(foundCustomer.get());

////    return LoginResponse.builder()
//            .code(400)
//            .message("Login failed")
//            .build();
    }


    private LoginResponse buildSuccessfulLoginResponse(Customer customer) {
        return LoginResponse.builder()
                .code(200)
                .message("Login successful")
                .build();
    }
}



