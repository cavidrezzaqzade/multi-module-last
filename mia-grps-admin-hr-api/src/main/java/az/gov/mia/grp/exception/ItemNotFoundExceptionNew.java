package az.gov.mia.grp.exception;

import az.gov.mia.grp.exception.GeneralException;

/**
 * @author Teyyub Aliyev
 * 4.22.2021
 */
public class ItemNotFoundExceptionNew extends GeneralException {

    public ItemNotFoundExceptionNew() {
        super();
    }

    public ItemNotFoundExceptionNew(String message) {
        super(message);
    }
}
