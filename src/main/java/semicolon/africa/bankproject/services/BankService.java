package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Bank;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;
import semicolon.africa.bankproject.dto.request.CustomerRegisterRequest;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.CustomerRegisterResponse;

public interface BankService {


    BankRegisterResponse registerBank(BankRegisterRequest bankRegisterRequest);

    BankRegisterResponse addNewBank(BankRegisterRequest bankRegisterRequest1);

    CustomerRegisterResponse registerCustomer(CustomerRegisterRequest customerRegister);
}

