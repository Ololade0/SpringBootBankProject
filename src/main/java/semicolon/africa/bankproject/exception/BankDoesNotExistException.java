package semicolon.africa.bankproject.exception;

import java.util.Date;

public class BankDoesNotExistException extends RuntimeException {


    public BankDoesNotExistException(String message){

        super(message);
    }

}
