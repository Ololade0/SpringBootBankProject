package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.request.UpdateBankRequest;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;
import semicolon.africa.bankproject.dto.response.UpdateBankResponse;

import java.util.List;

public interface BankService {
    BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest);
    BankRegisterResponse addNewBank(BankRegisterRequest bankRegisterRequest1);

    Bank getBankById(Long bankId);

    void deleteAll();

    long totalNumbersOfBanks();

    Customer findCustomerId(Long customerId);

    //List<Customer> findAllCustomers();

    CustomerRegisterResponse saveCustomer(CustomerRegisterRequest customerRegisterRequest);

    List<Bank> findAllBanks();


    void deleteById(Long bankId);

    UpdateBankResponse updateBankProfile(UpdateBankRequest updateBankRequest);

    long findTotalNumbersOfCustomers();
}

