package az.gov.mia.grp.exception.dto;

import lombok.*;

/**
 * @author Khayal Farziyev
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDTO {

    private Integer code;

    private String message;

    @Override
    public String toString() {
        return "ExceptionDTO{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}