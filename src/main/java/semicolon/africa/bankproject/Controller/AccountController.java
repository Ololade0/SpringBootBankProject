package semicolon.africa.bankproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import semicolon.africa.bankproject.dao.model.Account;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;
import semicolon.africa.bankproject.dto.request.DepositFundRequest;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.request.TransactionsRequest;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.dto.response.OpenAccountResponse;
import semicolon.africa.bankproject.exception.AccountCannotBeFound;
import semicolon.africa.bankproject.exception.BankDoesNotExistException;
import semicolon.africa.bankproject.services.AccountService;
;import java.math.BigDecimal;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountServices;
    @PostMapping("/account")
    public ResponseEntity<?> OpenAccount(@RequestBody OpenAccountRequest openAccountRequest) {
        try {
            Account openAccountResponse= accountServices.openAccount(openAccountRequest);
            return new ResponseEntity<>(openAccountResponse, HttpStatus.CREATED);
        } catch (AccountCannotBeFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/transaction")
    public ResponseEntity<?> depositTransactions(@RequestBody DepositFundRequest depositFundRequest) {
        try {
            BigDecimal transaction= accountServices.depositFundsIntoAccount(depositFundRequest);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (AccountCannotBeFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> recordTransactions(@RequestBody TransactionsRequest transactionsRequest) {
        try {
            Account transaction= accountServices.recordAccountTransaction(transactionsRequest);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (AccountCannotBeFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
