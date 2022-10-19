package semicolon.africa.bankproject.exception;

public class AccountAmountExceeded extends RuntimeException {
    public AccountAmountExceeded(String message) {
        super(message);
    }
}
