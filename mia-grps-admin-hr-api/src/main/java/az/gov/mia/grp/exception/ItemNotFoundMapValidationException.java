package az.gov.mia.grp.exception;

import java.util.Map;

/**
 * @author Teyyub Aliyev
 * 4.22.2021
 */
public class ItemNotFoundMapValidationException extends RuntimeException {

    private Map<String, String> errors;
//    public ItemNotFoundMapValidationException() {
//        super();
//    }
//
//    public ItemNotFoundMapValidationException(String message) {
//        super(message);
//    }
    public ItemNotFoundMapValidationException(Map<String, String> message) {

        errors = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
