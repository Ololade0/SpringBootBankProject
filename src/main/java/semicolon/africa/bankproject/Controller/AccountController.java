package semicolon.africa.bankproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import semicolon.africa.bankproject.dto.request.BankRegisterRequest;
import semicolon.africa.bankproject.dto.request.OpenAccountRequest;
import semicolon.africa.bankproject.dto.request.TransactionsRequest;
import semicolon.africa.bankproject.dto.response.BankRegisterResponse;
import semicolon.africa.bankproject.exception.BankDoesNotExistException;
import semicolon.africa.bankproject.services.AccountService;
;

@RestController
public class AccountController {
//    @Autowired
//    private AccountService accountServices;
//    @PostMapping("/banks")
//    public ResponseEntity<?> OPenAccount(@RequestBody TransactionsRequest transactionRequest) {
//        try {
////            BankRegisterResponse bankRegisterResponse = accountServices.(openAccountRequest);
//            return new ResponseEntity<>(bankRegisterResponse, HttpStatus.CREATED);
//        } catch (BankDoesNotExistException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

}
