package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.*;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;
import semicolon.africa.bankproject.dto.response.UpdateBankResponse;
import semicolon.africa.bankproject.dto.response.UpdateCustomerProfileResponse;

import java.util.List;

public interface BankService {
    BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest);


    Bank getBankById(Long bankId);

    void deleteAll();

    long totalNumbersOfBanks();

  //  Customer findCustomerId(Long customerId);

  //  List<Customer> findAllCustomers();

    CustomerRegisterResponse saveCustomer(CustomerRegisterRequest customerRegisterRequest);

    List<Bank> findAllBanks();


    void deleteById(Long bankId);

    UpdateBankResponse updateBankProfile(UpdateBankRequest updateBankRequest);

    long findTotalNumbersOfCustomers();

    List<Customer> findAllCustomers(FindAllCustomerRequest findAllCustomerRequest);


    void deleteCustomerById(DeleteCustomerRequest deleteCustomerRequest);


    String deleteALLCustomers(DeleteAllCustomerRequest deleteAllCustomerRequest);

    Customer findCustomerId(FindBankRequest findBankRequest);

    UpdateCustomerProfileResponse updateCustomerProfile(UpdateCustomerProfileRequest updateCustomerProfileRequest);
}

