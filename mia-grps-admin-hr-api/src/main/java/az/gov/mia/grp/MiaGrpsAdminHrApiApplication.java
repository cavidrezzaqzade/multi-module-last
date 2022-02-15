package az.gov.mia.grp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "az.gov.mia.grps")
public class MiaGrpsAdminHrApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiaGrpsAdminHrApiApplication.class, args);
    }

}
