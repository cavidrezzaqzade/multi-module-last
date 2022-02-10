package az.gov.mia.grp.tests;

import az.gov.mia.grp.MiaGrpsAdminHrServiceApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@ContextConfiguration
//@SpringBootTest//(classes = MiaGrpsAdminHrServiceApplication.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class) // bu olmayanda mock null olurdu
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT ,classes = MiaGrpsAdminHrServiceApplication.class)
  public class MiaGrpsAdminHrServiceApplicationTest {
    @Test
    public void testMethod(){

        System.out.println("test AdminHrServiceTest ");
    }

}
