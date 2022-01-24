package az.gov.mia.grp.exclude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Rasim R. İmanov
 */
@SpringBootApplication 
@ComponentScan(basePackages = "az.gov.mia.grp")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class); 
    }
}
