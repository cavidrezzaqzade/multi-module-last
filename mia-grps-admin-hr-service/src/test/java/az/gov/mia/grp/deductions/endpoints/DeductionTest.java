package az.gov.mia.grp.deductions.endpoints;

import az.gov.mia.grp.model.creatingTypeOfSalary.DeductionNameDTO;
import az.gov.mia.grp.repository.applicationTaxesAndSocialDeductions.DeductionsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // bu olmayanda mock null olurdu
//@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class DeductionTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeductionsRepository deductionsRepository;
    @Test
    public void create() throws Exception {
        this.mockMvc.perform(post("/adminhr/deductions"))
                .andExpect(status().isOk());
//                .andExpect(content().contentType(contentType));
//                .andExpect(jsonPath("$",iterableWithSize(5)))
//                .andExpect(jsonPath("$[0]['title']",containsString("SPRING_BOOT_MATCH")));
    }

    @Test
    public void postDeduction() throws Exception{

    }

}
