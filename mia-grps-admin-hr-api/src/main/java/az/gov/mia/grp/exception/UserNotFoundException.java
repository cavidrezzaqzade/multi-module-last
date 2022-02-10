package az.gov.mia.grp.exception;

/**
 * @author Khayal Farziyev
 */
public class UserNotFoundException extends GeneralException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}