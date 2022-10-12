package semicolon.africa.bankproject.services;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.repository.BankRepository;
import semicolon.africa.bankproject.dto.request.*;

import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;
import semicolon.africa.bankproject.dto.response.UpdateBankResponse;
import semicolon.africa.bankproject.dto.response.UpdateCustomerProfileResponse;
import semicolon.africa.bankproject.exception.BankDoesNotExistException;


import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor

public class BankServiceImpl implements BankService{
    @Autowired
    private final BankRepository bankRepository;
    @Autowired
    private final CustomerService customerService;


    @Override
    public BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest) {
        Bank bank = new Bank();
              bank.setBankLocation(bankRegisterRequest.getBanklocation());
              bank.setBankName(bankRegisterRequest.getBankName());
       Bank savedBank = bankRepository.save(bank);
       BankRegisterResponse bankRegisterResponse = new BankRegisterResponse();
       bankRegisterResponse.setMessage("Bank successfully registered");
       bankRegisterResponse.setBankId(savedBank.getId());
        return bankRegisterResponse;
    }





    @Override
    public Bank getBankById(Long bankId) {
        return bankRepository.findById(bankId).orElseThrow(
                ()->new BankDoesNotExistException(
                        String.format("bank with id %d not found", bankId)
                )
        );

    }

    @Override
    public void deleteAll() {
        bankRepository.deleteAll();

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
    public void deleteById(Long bankId) {
        bankRepository.deleteById(bankId);

    }


    @Override
    public UpdateBankResponse updateBankProfile(UpdateBankRequest updateBankRequest) {
        Bank foundBank = bankRepository.findBankById(updateBankRequest.getBankId());
        if(updateBankRequest.getBankName() != null){
            foundBank.setBankName(updateBankRequest.getBankName());
        }
        if(updateBankRequest.getBankLocation() != null){
            foundBank.setBankLocation(updateBankRequest.getBankLocation());
        }
        bankRepository.save(foundBank);
        return UpdateBankResponse.builder()
                .message("Bank successfully updated")
                .build();
    }

    @Override
    public CustomerRegisterResponse saveCustomer(CustomerRegisterRequest customerRegisterRequest) {
        Customer customer= customerService.saveNewCustomer(customerRegisterRequest);
      Bank foundBank =  bankRepository.findBankById(customerRegisterRequest.getBankId());
        if(foundBank != null){
            foundBank.getCustomers().add(customer);
            bankRepository.save(foundBank);
        }
        CustomerRegisterResponse customerRegisterResponse = new CustomerRegisterResponse();
        customerRegisterResponse.setMessage("Customer successfully registered");
        customerRegisterResponse.setCustomerId(customer.getCustomerId());
        customerRegisterResponse.setCustomerId(customer.getCustomerId());
        return customerRegisterResponse;
    }

    @Override
    public long findTotalNumbersOfCustomers() {
        return customerService.totalNumberOfCustomer();
    }

    @Override
    public List<Customer> findAllCustomers(FindAllCustomerRequest findAllCustomerRequest) {
       Bank foundBank =  bankRepository.findBankById(findAllCustomerRequest.getBankId());
        if(foundBank != null){
            return customerService.findAllCustomers();
        }
        throw new BankDoesNotExistException("Bank cannot be found");
    }

    @Override
    public void deleteCustomerById(DeleteCustomerRequest deleteCustomerRequest) {
        Bank foundBank = bankRepository.findBankById(deleteCustomerRequest.getBankId());
        if(foundBank != null){
            for(var customerToDel : foundBank.getCustomers()){
                if(Objects.equals(customerToDel.getCustomerId(), deleteCustomerRequest.getCustomerId())){
                    customerService.deleteCustomer(deleteCustomerRequest.getCustomerId());
                    foundBank.getCustomers().remove(customerToDel);
                    bankRepository.save(foundBank);
                }
            }
        }

        

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
    public Customer findCustomerId(FindBankRequest findBankRequest) {
        Bank foundBank = bankRepository.findBankById(findBankRequest.getBankId());
        if(foundBank != null){
            return customerService.findCustomerById(findBankRequest.getCustomerId());
        }
        throw new BankDoesNotExistException("Bank cannot be found");
    }

    @Override
    public UpdateCustomerProfileResponse updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest) {;
         Customer foundCustomer = customerService.updateCustomerProfile(updateCustomerProfileRequest);
        Bank foundBank = bankRepository.findBankById(updateCustomerProfileRequest.getBankId());
        if(foundBank != null) {
            foundBank.getCustomers().add(foundCustomer);
            bankRepository.save(foundBank);

        }
        UpdateCustomerProfileResponse updateCustomerProfileResponse = new UpdateCustomerProfileResponse();
        updateCustomerProfileResponse.setMessage("Customer profile successfully updated");
        return updateCustomerProfileResponse;
    }


//    @Override
//    public Customer findCustomerId(Long customerId) {
//        Bank foundBank = bankRepository.findBankById(customerId);
//        if(foundBank != null){
//            return customerService.findCustomerById(customerId);
//        }
//        throw new BankDoesNotExistException("Bank Cannot be found");
//        }

    }



