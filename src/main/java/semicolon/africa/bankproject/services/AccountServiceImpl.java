package semicolon.africa.bankproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dao.repository.AccountRepository;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public OpenAccountResponse openAccount(OpenAccountRequest openAccountRequest) {
        Account newAccount = Account.builder()
                        .AccountName(openAccountRequest.getAccountName())
                        .age(openAccountRequest.getCustomerAge())
                        .gender(openAccountRequest.getCustomerGender())

                .build();
        accountRepository.save(newAccount);
        OpenAccountResponse openAccountResponse = new OpenAccountResponse();
        openAccountResponse.setMessage("Account successfully opened");
        return openAccountResponse;
    }



}

