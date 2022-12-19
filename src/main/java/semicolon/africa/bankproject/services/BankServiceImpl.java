package semicolon.africa.bankproject.services;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.repository.BankRepository;
import semicolon.africa.bankproject.dto.request.*;

import semicolon.africa.bankproject.dto.response.*;
import semicolon.africa.bankproject.exception.AccountCannotBeFound;
import semicolon.africa.bankproject.exception.BankDoesNotExistException;
import semicolon.africa.bankproject.exception.EXceptionHandler.ErrorMessage;
import semicolon.africa.bankproject.utils.Utils;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;
    private final CustomerService customerService;
    private final AccountService accountService;
    private final Utils utils;

    @Override
    public BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest) throws BankDoesNotExistException{
        Bank bank = new Bank();
        bank.setBankName(bankRegisterRequest.getBankName());
        bank.setBankLocation(bankRegisterRequest.getBanklocation());
        Bank savedBank = bankRepository.save(bank);
        BankRegisterResponse bankRegisterResponse = new BankRegisterResponse();
        bankRegisterResponse.setMessage("Bank successfully registered");
        bankRegisterResponse.setBankId(savedBank.getId());
        bankRegisterResponse.setBankLocation(savedBank.getBankLocation());
        return bankRegisterResponse;
    }


    @Override
    public Bank  findBankById(String bankId) throws BankDoesNotExistException{
//
        Bank foundBank = bankRepository.findBankById(bankId);
        if (foundBank != null){
            return foundBank;
        }
       throw new BankDoesNotExistException(ErrorMessage.BankDoesNotExist.getErrorMessage());

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
    public Page<Bank> findAllBanks(FindAllBankRequest findAllBankRequest) {
        Pageable pageable = PageRequest.of(findAllBankRequest.getPageNumber()-1, findAllBankRequest.getNumberOfPages());
        return bankRepository.findAll(pageable);


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
     Bank response =   bankRepository.save(foundBank);
        return UpdateBankResponse.builder()
                .message("Bank successfully updated")
                .bankName(response.getBankName())
                .build();
    }

    @Override
    public CustomerRegisterResponse saveCustomer(CustomerRegisterRequest customerRegisterRequest)  {
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
    public Page<Customer> findAllCustomers(FindAllCustomerRequest findAllCustomerRequest) throws BankDoesNotExistException{
        Bank foundBank = bankRepository.findBankById(findAllCustomerRequest.getBankId());
        if (foundBank != null) {
            return customerService.findAllCustomers(findAllCustomerRequest);
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
        if (foundBank != null) {
            foundBank.getAccounts().add(newAccount);
            bankRepository.save(foundBank);
        }
        return OpenAccountResponse.builder()
                .message("Account suuccessfully registered")
                .id(newAccount.getId())
                .build();

    }

    @Override
    public long findTotalNumbersOfAccounts() {
        return accountService.totalNumberOfAccount();
    }


    @Override
    public String deleteAllAccount() {
//        customerService.deleteAllAccounts();
        accountService.deleteAll();
        return "All account successfully deleted";
    }


    @Override
    public String deleteAccountById(DeleteAccountRequest deleteAccountRequest) {
        Bank foundBank = bankRepository.findBankById(deleteAccountRequest.getBankId());
        Customer foundCustomer = customerService.findCustomerById(deleteAccountRequest.getCustomerId());
        if (foundBank != null) {
            List<Account> accounts = foundCustomer.getAccounts();
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getId().equalsIgnoreCase(deleteAccountRequest.getAccountId())) {
                   // customerService.deleteAccountById(deleteAccountRequest);
                    accountService.deleteBYId(deleteAccountRequest.getAccountId());
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
        if(foundBank!= null){
            foundBank.getAccounts().add(updatedAccount);
        }
       bankRepository.save(foundBank);
       return UpdateAccountResponse.builder()
                .message("Account successfully updated")
               .accountName(updatedAccount.getAccountName())
                .build();
    }

    @Override
    public Account findAccountByAccountName(String accountName) {
        return accountService.findAccountByAccountName(accountName);
    }

    @Override
    public Account findAccountByAccountNUmber(String accountNumber) {
        return accountService.findAccountByAccountNUmber(accountNumber);
    }

    @Override
    public Account findAccountByAccountNames(String accountName) {
        return accountService.findAccountByAccountNames(accountName);
    }

    @Override
    public Account findAccountById(FindAccountRequest findAccountRequest) {
       Account foundAccount = accountService.findAccountById(findAccountRequest.getAccoundId());
       if(foundAccount!=null){
           return accountService.findAccountById(findAccountRequest.getAccoundId());
       }
       else {
           throw new AccountCannotBeFound("Account cannot be found");
       }

    }

    @Override
    public Page<Account> findAllAccounts(FindAllAccountRequest findAllAccountRequest) {
        Bank foundBank = bankRepository.findBankById(findAllAccountRequest.getBankId());
        if (foundBank != null) {
            return accountService.findAllAccount(findAllAccountRequest);

        } else {
            throw new BankDoesNotExistException("Bank does not Exist");
        }

    }
}










