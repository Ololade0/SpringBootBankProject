package semicolon.africa.bankproject.services;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.repository.BankRepository;
import semicolon.africa.bankproject.dto.request.*;

import semicolon.africa.bankproject.dto.response.*;
import semicolon.africa.bankproject.exception.AccountCannotBeFound;
import semicolon.africa.bankproject.exception.BankDoesNotExistException;

import java.util.HashSet;
import java.util.List;



@Service
@AllArgsConstructor

public class BankServiceImpl implements BankService {
    @Autowired
    private final BankRepository bankRepository;
    @Autowired
    private final CustomerService customerService;
    @Autowired
    private final AccountService accountService;


    @Override
    public BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest) {
        Bank bank = new Bank();
        bank.setBankLocation(bankRegisterRequest.getBanklocation());
        bank.setBankName(bankRegisterRequest.getBankName());
        Bank savedBank = bankRepository.save(bank);
        BankRegisterResponse bankRegisterResponse = new BankRegisterResponse();
        bankRegisterResponse.setMessage("Bank successfully registered");
        bankRegisterResponse.setBankId(savedBank.getId());
        bankRegisterResponse.setBankLocation(bank.getBankLocation());
        return bankRegisterResponse;
    }


    @Override
    public Bank findBankById(String bankId) {
        return bankRepository.findBankById(bankId);
//        return bankRepository.findById(bankId).orElseThrow(
//                () -> new BankDoesNotExistException(
//                        String.format("bank with id %d not found", bankId)
//                )
//        );

    }

    @Override
    public String deleteAll() {
        bankRepository.deleteAll();
        return "All Banks successfully deleted";

    }

    @Override
    public long totalNumbersOfBanks() {
        return bankRepository.count();
    }


    @Override
    public List<Bank> findAllBanks() {
        return bankRepository.findAll();

    }

    @Override
    public String deleteById(String bankId) {
        bankRepository.deleteById(bankId);
        return "Bank successfully deleted";

    }


    @Override
    public UpdateBankResponse updateBankProfile(UpdateBankRequest updateBankRequest) {
        Bank foundBank = bankRepository.findBankById(updateBankRequest.getBankId());
        if (updateBankRequest.getBankName() != null) {
            foundBank.setBankName(updateBankRequest.getBankName());
        }
        if (updateBankRequest.getBankLocation() != null) {
            foundBank.setBankLocation(updateBankRequest.getBankLocation());
        }
        bankRepository.save(foundBank);
        return UpdateBankResponse.builder()
                .message("Bank successfully updated")
                .build();
    }

    @Override
    public CustomerRegisterResponse saveCustomer(CustomerRegisterRequest customerRegisterRequest) {
        var customer = customerService.saveNewCustomer(customerRegisterRequest);
        Bank foundBank = bankRepository.findBankById(customerRegisterRequest.getBankId());
        if (foundBank != null) {
            foundBank.getCustomers().add(customer);
            bankRepository.save(foundBank);
        }
        CustomerRegisterResponse customerRegisterResponse = new CustomerRegisterResponse();
        customerRegisterResponse.setMessage("Customer successfully registered");
        customerRegisterResponse.setCustomerId(customer.getCustomerId());
        customerRegisterResponse.setCustomerName(customer.getCustomerName());

        return customerRegisterResponse;
    }

    @Override
    public long findTotalNumbersOfCustomers() {
        return customerService.totalNumberOfCustomer();
    }

    @Override
    public List<Customer> findAllCustomers(FindAllCustomerRequest findAllCustomerRequest) {
        Bank foundBank = bankRepository.findBankById(findAllCustomerRequest.getBankId());
        if (foundBank != null) {
            return customerService.findAllCustomers();
        }
        throw new BankDoesNotExistException("Bank cannot be found");
    }

    @Override
    public String deleteCustomerById(DeleteCustomerRequest deleteCustomerRequest) {
        Bank foundBank = bankRepository.findBankById(deleteCustomerRequest.getBankId());
        if (foundBank != null) {
            List<Customer> customers = foundBank.getCustomers();
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getCustomerId().equalsIgnoreCase(deleteCustomerRequest.getCustomerId())) {
                    customerService.deleteCustomerById(deleteCustomerRequest.getCustomerId());
                    customers.remove(customers.get(i));
                    bankRepository.save(foundBank);
                }
//
            }
            return "Customer successfully deleted";
        }
        return "error";
    }


    @Override
    public String deleteALLCustomers(DeleteAllCustomerRequest deleteAllCustomerRequest) {
        var foundBank = bankRepository.findBankById(deleteAllCustomerRequest.getBankId());
        if (foundBank != null) {
            customerService.deleteAll();
            return "Customer successfully deleted";
        }
        return "error";
    }


    @Override
    public Customer findCustomerId(FindCustomerRequest findCustomerRequest) {
        Bank foundBank = bankRepository.findBankById(findCustomerRequest.getBankId());
        if (foundBank != null) {
            return customerService.findCustomerById(findCustomerRequest.getCustomerId());
        }
        throw new BankDoesNotExistException("Bank cannot be found");
    }

    @Override
    public UpdateCustomerProfileResponse updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest) {
        ;
        Customer foundCustomer = customerService.updateCustomerProfile(updateCustomerProfileRequest);
        Bank foundBank = bankRepository.findBankById(updateCustomerProfileRequest.getBankId());
        if (foundBank != null) {
            foundBank.getCustomers().add(foundCustomer);
            bankRepository.save(foundBank);

        }
        UpdateCustomerProfileResponse updateCustomerProfileResponse = new UpdateCustomerProfileResponse();
        updateCustomerProfileResponse.setMessage("Customer profile successfully updated");
        return updateCustomerProfileResponse;
    }

    @Override
    public void deletedAllCustomers() {
        customerService.deleteAll();

    }

    @Override
    public OpenAccountResponse openCustomerAccount(OpenAccountRequest openAccountRequest) {
        Account newAccount = accountService.openAccount(openAccountRequest);
        Bank foundBank = bankRepository.findBankById(openAccountRequest.getBankId());
        Customer foundCustomer = customerService.findCustomerById(openAccountRequest.getCustomerId());
        if (foundBank != null) {
            foundBank.getCustomers().add(foundCustomer);
            foundCustomer.getAccounts().add(newAccount);
            bankRepository.save(foundBank);
        }
        return OpenAccountResponse.builder()
                .message("Account suuccessfully registered")
                .accountName(newAccount.getAccountName())
                .id(newAccount.getId())
                .build();

    }

    @Override
    public long findTotalNumbersOfAccounts() {
        return accountService.totalNumberOfAccount();
    }

    @Override
    public Account findAccountById(FindAccountRequest findAccountRequest) {
        Bank foundBank = bankRepository.findBankById(findAccountRequest.getBankId());
        Customer foundCustomer = customerService.findCustomerById(findAccountRequest.getCustomerId());
        if (foundBank != null && foundCustomer != null) {
            return customerService.findAccountById(findAccountRequest);
        }
        return null;
    }

    @Override
    public String deleteAllAccount() {
        customerService.deleteAllAccounts();
        accountService.deleteAll();
        return "All account successfully deleted";
    }

    @Override
    public List<Account> findAllAccounts(FindAllAccountRequest findAllAccountRequest) {
        Bank foundBank = bankRepository.findBankById(findAllAccountRequest.getBankId());
        Customer foundCustomer = customerService.findCustomerById(findAllAccountRequest.getCustomerId());
        if (foundCustomer != null && foundBank != null) {
            return customerService.findAllAccounts(findAllAccountRequest);
        }
        throw new BankDoesNotExistException(foundBank.getBankName() + " does not exist");
    }

    @Override
    public String deleteAccountById(DeleteAccountRequest deleteAccountRequest) {
        Bank foundBank = bankRepository.findBankById(deleteAccountRequest.getBankId());
        Customer foundCustomer = customerService.findCustomerById(deleteAccountRequest.getCustomerId());
        if (foundBank != null) {
            List<Account> accounts = foundCustomer.getAccounts();
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getId().equalsIgnoreCase(deleteAccountRequest.getAccountId())) {
                    customerService.deleteAccountById(deleteAccountRequest);
                  //  accountService.deleteBYId(deleteAccountRequest.getAccountId());
                    accounts.remove(accounts.get(i));
                    bankRepository.save(foundBank);
                    return " Account successfully deleted";
                }
            }
        }
        return "error";

    }

    @Override
    public UpdateAccountResponse updateAccountProfile(UpdateAccountRequest updateAccountRequest) {
        Account updatedAccount = accountService.updateAccount(updateAccountRequest);
        Bank foundBank = bankRepository.findBankById(updateAccountRequest.getBankId());
        Customer foundCustomer = customerService.findCustomerById(updateAccountRequest.getCustomerId());
        if(foundBank!= null && foundCustomer!= null){
            foundBank.getCustomers().add(foundCustomer);
            foundCustomer.getAccounts().add(updatedAccount);
            bankRepository.save(foundBank);
        }
       return UpdateAccountResponse.builder()
                .message("Account successfully updated")
                .build();
    }

}










