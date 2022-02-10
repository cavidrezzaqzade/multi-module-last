package az.gov.mia.grp.exception;

/**
 * @author Cavid Aslanov
 * @time 20/05/2021 - 9:04 PM
 **/
public class DataIntegrityException extends RuntimeException{

    public DataIntegrityException() {
        super();
    }

    public DataIntegrityException(String message) {
        super(message);
    }
}
