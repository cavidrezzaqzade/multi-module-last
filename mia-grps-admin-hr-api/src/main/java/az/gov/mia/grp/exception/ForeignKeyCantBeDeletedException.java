package az.gov.mia.grp.exception;

/**
 * @author Khayal Farziyev
 */
public class ForeignKeyCantBeDeletedException extends GeneralException {

    public ForeignKeyCantBeDeletedException() {
        super();
    }

    public ForeignKeyCantBeDeletedException(String message) {
        super(message);
    }
}
