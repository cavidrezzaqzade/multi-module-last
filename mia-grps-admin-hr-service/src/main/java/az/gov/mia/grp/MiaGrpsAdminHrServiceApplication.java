package az.gov.mia.grp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
//@EnableResourceServer
public class MiaGrpsAdminHrServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiaGrpsAdminHrServiceApplication.class, args);
	}
}