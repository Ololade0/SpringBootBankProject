package semicolon.africa.bankproject.exception;

public class CustomerCannotBeFound extends RuntimeException{

    public CustomerCannotBeFound(String message) {
        super(message);
    }
}

