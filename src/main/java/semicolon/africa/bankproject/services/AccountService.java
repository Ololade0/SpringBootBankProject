package semicolon.africa.bankproject.services;

import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;

public interface AccountService {


    OpenAccountResponse openAccount(OpenAccountRequest openAccountRequest);
}
