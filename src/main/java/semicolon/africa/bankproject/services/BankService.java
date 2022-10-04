package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;

import java.util.List;

public interface BankService {


    BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest);
    BankRegisterResponse addNewBank(BankRegisterRequest bankRegisterRequest1);




    Bank getBankById(Long bankId);

    void deleteAll();

    long totalNumbersOfBanks();

    Customer findCustomerId(Long customerId);

    List<Customer> findAllCustomers();

    CustomerRegisterResponse saveCustomer(CustomerRegisterRequest customerRegisterRequest);
}

