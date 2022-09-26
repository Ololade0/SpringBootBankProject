package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dao.model.Customer;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;

import java.util.Collection;
import java.util.List;

public interface BankService {
    BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest);

    long totalNumbersOfBanks();

    CustomerRegisterResponse registerCustomer(CustomerRegisterRequest customerRegister);



    List<Customer> getAllCustomers();

        void deleteAll();


//   BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest);
//
//   long totalNumbersOfBanks();
//

//
//
//
//    long totalNumbersOfCustomers();
//
//    CustomerRegisterResponse registerCustomer(CustomerRegisterRequest customerRegister);
//
//
//    Bank findBankByBankId(Long id);
//
//    OpenAccountResponse openAccount(OpenAccountRequest openAccountRequest);
//
//    List<Account> findAllAccounts();
//
//    long size();
}
