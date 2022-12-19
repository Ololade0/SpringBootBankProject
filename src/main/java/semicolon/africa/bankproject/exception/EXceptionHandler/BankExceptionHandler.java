package semicolon.africa.bankproject.exception.EXceptionHandler;
import com.mongodb.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import semicolon.africa.bankproject.exception.AccountCannotBeFound;
import semicolon.africa.bankproject.exception.BankDoesNotExistException;
import semicolon.africa.bankproject.exception.CustomerCannotBeFound;

import java.util.Date;

@ControllerAdvice
public class BankExceptionHandler{
    @ExceptionHandler(value = {BankDoesNotExistException.class})
    public ResponseEntity<Object> handleBankServiceException(BankDoesNotExistException exception, WebRequest request) {
        ApiError errorMessage = new ApiError(new Date(), exception.getMessage());
        return new ResponseEntity<>(errorMessage.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(value = {AccountCannotBeFound.class})
    public ResponseEntity<Object> handleAccountServiceException(AccountCannotBeFound exception, WebRequest request) {
        ApiError errorMessage = new ApiError(new Date(), exception.getMessage());
        return new ResponseEntity<>(errorMessage.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = {CustomerCannotBeFound.class})
    public ResponseEntity<Object> handleCustomerServiceException(CustomerCannotBeFound exception, HttpStatus status, WebRequest request) {
        ApiError errorMessage = new ApiError(new Date(), exception.getMessage());
        return new ResponseEntity<>(errorMessage.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }


}


