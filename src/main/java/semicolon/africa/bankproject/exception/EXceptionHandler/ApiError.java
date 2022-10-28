package semicolon.africa.bankproject.exception.EXceptionHandler;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ApiError {
    private String message;
    private Date timeStamp;
    private HttpStatus status;;
    private List<String> errors;

    public ApiError(final HttpStatus status, final String message, final List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(final HttpStatus status, final String message, final String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    //

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(final HttpStatus status) {
        this.status = status;
    }
    public List<String> getErrors() {

        return errors;
    }

    public void setErrors(final List<String> errors) {

        this.errors = errors;
    }

    public void setError(final String error) {

        errors = Arrays.asList(error);
    }
























    public ApiError(String message, Date timeStamp) {
        super();
        this.message = message;
        this.timeStamp = timeStamp;
    }
    public ApiError() {

    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
