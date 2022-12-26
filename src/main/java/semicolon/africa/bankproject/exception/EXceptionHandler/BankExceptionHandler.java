package semicolon.africa.bankproject.exception.EXceptionHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import semicolon.africa.bankproject.exception.*;

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

    @ExceptionHandler(value = {AccountAmountException.class})
    public ResponseEntity<Object> handleAccountAmountException(AccountAmountException exception, HttpStatus status, WebRequest request) {
        ApiError errorMessage = new ApiError(new Date(), exception.getMessage());
        return new ResponseEntity<>(errorMessage.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(value = {BankNameAlreadyExistException.class})
    public ResponseEntity<Object> handleBankNameAlreadyExist(BankNameAlreadyExistException exception, HttpStatus status, WebRequest request) {
        ApiError errorMessage = new ApiError(new Date(), exception.getMessage());
        return new ResponseEntity<>(errorMessage.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IncorrectPasswordException.class})
    public ResponseEntity<Object> handleIncorrectPasswordException(IncorrectPasswordException exception, HttpStatus status, WebRequest request) {
        ApiError errorMessage = new ApiError(new Date(), exception.getMessage());
        return new ResponseEntity<>(errorMessage.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }




}


