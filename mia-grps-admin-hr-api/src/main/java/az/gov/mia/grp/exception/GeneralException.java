package az.gov.mia.grp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Khayal Farziyev
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class GeneralException extends RuntimeException {

    public GeneralException() {
        super();
    }

    public GeneralException(String message) {
        super(message);
    }
}