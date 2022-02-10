package az.gov.mia.grp.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cavid Aslanov
 * @time 15/02/2021 - 11:14 AM
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
