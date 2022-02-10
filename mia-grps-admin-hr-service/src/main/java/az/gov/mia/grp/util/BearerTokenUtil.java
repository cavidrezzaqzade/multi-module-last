package az.gov.mia.grp.util;

/**
 * @author Cavid Aslanov
 * @time 24/02/2021 - 3:10 PM
 **/
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BearerTokenUtil {

    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization: ");
    }
}