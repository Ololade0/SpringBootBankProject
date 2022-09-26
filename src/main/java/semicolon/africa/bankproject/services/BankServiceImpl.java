package semicolon.africa.bankproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.repository.BankRepository;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.response.AccountRegisterResponse;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;
import semicolon.africa.bankproject.exception.BankDoesNotExistException;

import java.util.List;

@Service
public class BankServiceImpl implements BankService{
       @Autowired
    private BankRepository bankRepository;

        @Autowired
    private CustomerService customerService;
    @Override
    public BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest) {
        Bank foundBank = Bank.builder()
                .bankName(bankRegisterRequest.getBankName())
                .bankLocation(bankRegisterRequest.getBankLOcation()).
                build();
        Bank savedBank = bankRepository.save(foundBank);
        BankRegisterResponse bankRegisterResponse = new BankRegisterResponse();
        bankRegisterResponse.setMessage("Bank registered");
        bankRegisterResponse.setId(savedBank.getBankId());
        return bankRegisterResponse;
       // return BankRegisterResponse.builder().message("Bank successfully registered").build();


    }

    @Override
    public long totalNumbersOfBanks() {
        return bankRepository.count();
    }

    @Override
    public CustomerRegisterResponse registerCustomer(CustomerRegisterRequest customerRegister) {
        Customer newCustomer = Customer
                .builder()
                .customerName(customerRegister.getCustomerName())
                .customerAge(customerRegister.getCustomerAge())
                .customerGender(customerRegister.getCustomerGender())
                .build();

        Bank foundBank = bankRepository.findBankByBankId(customerRegister.getBankId());

        if(foundBank != null){
            foundBank.getCustomers().add(newCustomer);
            customerService.saveNewCustomers(newCustomer);
            bankRepository.save(foundBank);
        }
                if(foundBank == null){
            throw  new BankDoesNotExistException("Bank does not exist");
        }
        CustomerRegisterResponse customerRegisterResponse = new CustomerRegisterResponse();
        customerRegisterResponse.setMessage("Customer successfully registered");
        return customerRegisterResponse;
     }

    @Override
    public List<Customer> getAllCustomers() {
        return bankRepository.findAll().get(0).getCustomers();
    }


//    @Override
//    public long totalNumbersOfCustomers() {
//       return customerService.totalNUmbersOfCustomers();
//    }
//
//    @Override
//    public CustomerRegisterResponse registerCustomer(CustomerRegisterRequest customerRegister) {
//        Customer customer = new Customer();
//        customer.setCustomerName(customerRegister.getCustomerName());
//        customer.setCustomerAge(customerRegister.getCustomerAge());
//        Bank foundBank = bankRepository.findBankByBankId(customerRegister.getBankId());
//        if(foundBank != null){
//            foundBank.getCustomers().add(customer);
//            customerService.saveNewCustomer(customer);
//            bankRepository.save(foundBank);
//        }

//    @Override
//    public Bank findBankByBankId(Long id) {
//
//        return bankRepository.findBankByBankId(id);
//    }
//
//    @Override
//    public OpenAccountResponse openAccount(OpenAccountRequest openAccountRequest) {
//        Account account = new Account();
//        account.setAccountName(openAccountRequest.getAccountName());
//        account.setAge(openAccountRequest.getCustomerAge());
//        account.setGender(openAccountRequest.getCustomerGender());
//       Bank bank = bankRepository.findBankByBankId(openAccountRequest.getBankId());
//       if(bank != null){
//           bank.getAccounts().add(account);
//           accountService.saveNewAccount(account);
//           bankRepository.save(bank);
//
//
//       }
//      OpenAccountResponse openAccountResponse = new OpenAccountResponse();
//       openAccountResponse.setMessage("");
//       return openAccountResponse;
//       // return null;
//    }
//
//    @Override
//    public List<Account> findAllAccounts() {
//       return bankRepository.findAll().get(0).getAccounts();
//       // return accountService.findAll();
//    }
//
//    @Override
//    public long size() {
//        return bankRepository.count();
//    }
//
//
//    @Override
//    public long totalNumbersOfBanks() {
//        return bankRepository.count();
//    }
//
    @Override
    public void deleteAll() {
    bankRepository.deleteAll();
    }


}
