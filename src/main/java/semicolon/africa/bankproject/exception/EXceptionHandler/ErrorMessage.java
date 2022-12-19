package semicolon.africa.bankproject.exception.EXceptionHandler;


public enum ErrorMessage  {
    BankDoesNotExist("Bank cannot be found"),
    RECORDALREADYEXIST("Record alrady exist ");


    ErrorMessage(String message) {
        this.errorMessage = message;
    }
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
