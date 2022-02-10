package az.gov.mia.grp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class NameAlreadyExistException extends GeneralException {

    public NameAlreadyExistException() {
        super();
    }

    public NameAlreadyExistException(String message) {
        super(message);
    }
}
