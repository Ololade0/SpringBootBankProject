package semicolon.africa.bankproject.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import semicolon.africa.bankproject.dao.model.Bank;

import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dao.repository.BankRepository;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;

import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;
import semicolon.africa.bankproject.exception.BankDoesNotExistException;

import java.util.List;


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
    public BankRegisterResponse addNewBank(BankRegisterRequest bankRegisterRequest1) {
                Bank newBank = Bank.builder().bankName(bankRegisterRequest1.getBankName())
                .bankLocation(bankRegisterRequest1.getBanklocation())
                .build();
        Bank savedBank1= bankRepository.save(newBank);
        BankRegisterResponse bankRegisterResponse = new BankRegisterResponse();
        bankRegisterResponse.setMessage("Another newBank successfully registered");
       bankRegisterResponse.setBankId(savedBank1.getId());
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
    public Customer findCustomerId(Long customerId) {
       return customerService.findCustomerById(customerId);

    }

    @Override
    public List<Customer> findAllCustomers() {
        return  bankRepository.findAll().get(0).getCustomers();
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
        return customerRegisterResponse;
    }

}

