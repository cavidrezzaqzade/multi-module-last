package az.gov.mia.grp.test.model;

import java.util.Date;

/**
 *
 * @author Rasim R. İmanov
 */
public class EchoDto {

    private String message;
    private Date ts;

    public EchoDto() {
    }

    public EchoDto(String message, Date ts) {
        this.message = message;
        this.ts = ts;
    }

    public EchoDto(String message) {
        this(message, new Date());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    
}
