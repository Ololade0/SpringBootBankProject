package semicolon.africa.bankproject.exception;

public class BankDoesNotExistException extends RuntimeException {


    public BankDoesNotExistException(String message){

        super(message);
    }
}
