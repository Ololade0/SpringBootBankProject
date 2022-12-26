package semicolon.africa.bankproject.exception;

public class AccountCannotBeFound extends RuntimeException{
    public AccountCannotBeFound(String message) {
    }

    public static String AccountCannotBeFound(String accountNumber, String password) {
        return  accountNumber + "or " + password + "cannot be found";
    }
}
