package semicolon.africa.bankproject.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BankNameAlreadyExistException extends RuntimeException{
    private String message;
    private int statusCode;
}
