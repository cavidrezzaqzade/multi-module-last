package az.gov.mia.grp.exception.dto;

import lombok.Data;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class ErrorDetails {
//    private Date timestamp;
//    private Integer code;
    private String message;
//    private String details;

    public ErrorDetails(String error) {
//        this.code = code;
        this.message = error;
    }
}