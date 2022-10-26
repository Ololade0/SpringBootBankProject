package semicolon.africa.bankproject.exception.EXceptionHandler;
import com.mongodb.lang.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import semicolon.africa.bankproject.exception.AccountCannotBeFound;
import semicolon.africa.bankproject.exception.BankDoesNotExistException;
import semicolon.africa.bankproject.exception.CustomerCannotBeFound;

import java.util.Date;

@ControllerAdvice
public class BankExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {BankDoesNotExistException.class})
    public ResponseEntity<Object> handleBankServiceException(BankDoesNotExistException exception, WebRequest request){
       ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), new Date());
        return new ResponseEntity<>(errorMessage.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(value = {AccountCannotBeFound.class})
    public ResponseEntity<Object> handleAccountServiceException(AccountCannotBeFound exception, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), new Date());
        return new ResponseEntity<>(errorMessage.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = {CustomerCannotBeFound.class})
    public ResponseEntity<Object> handleCustomerServiceException(CustomerCannotBeFound exception, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), new Date());
        return new ResponseEntity<>(errorMessage.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
//    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<Object> HttpMessageNotReadableException(HttpMessageNotReadableException exception, @Nullable WebRequest request){
        final String error = exception.getMessage() + " not readable";
        ErrorMessage errorMessage = new ErrorMessage(error, new Date());
        return new ResponseEntity<>(errorMessage.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}


