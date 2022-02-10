package az.gov.mia.grp.exception.dto;

/**
 * @author Khayal Farziyev
 */
public enum DefaultException {

    USER_NOT_FOUND("UserDto not founded"),
    NAME_ALREADY_EXIST("This name already exist.");

    private final String value;

    DefaultException(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}