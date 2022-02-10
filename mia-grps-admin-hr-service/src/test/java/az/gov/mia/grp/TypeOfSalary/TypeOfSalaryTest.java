package az.gov.mia.grp.TypeOfSalary;


import az.gov.mia.grp.entity.creatingTypeOfSalary.TypeOfSalaryEntity;
import az.gov.mia.grp.model.creatingTypeOfSalary.TypeOfSalaryBaseDTO;
import az.gov.mia.grp.model.creatingTypeOfSalary.TypeOfSalaryDTO;
import az.gov.mia.grp.repository.creatingTypeOfSalary.TypeOfSalaryRepository;
import az.gov.mia.grp.response.PageableResponse;
import az.gov.mia.grp.service.creatingTypeOfSalary.TypeOfSalaryService;
import az.gov.mia.grp.tests.MiaGrpsAdminHrServiceApplicationTest;
import lombok.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO: 5/16/2021  Məvacib növünün yaradılması forması test
//@RunWith(SpringRunner.class) // bu olmayanda mock null olurdu
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//@WebAppConfiguration
//@AutoConfigureMockMvc

public class TypeOfSalaryTest extends MiaGrpsAdminHrServiceApplicationTest {
    // TODO: 5/16/2021 @Autowired ile islemir
//    @MockBean
    @Autowired
    private TypeOfSalaryService service;

    @Autowired
    private TypeOfSalaryRepository repository;

    // TODO: 5/17/2021 ilkin testler
    @Test
//    @Transactional
//    @Commit
    public void postTest() throws Exception{
        TypeOfSalaryBaseDTO dto = new TypeOfSalaryBaseDTO();
        dto.setSalaryName("test");
        dto.setSalaryCode("101");
        dto.setPaymentPeriodicityId(1L);
        service.add1(dto);

        TypeOfSalaryBaseDTO dto1 = new TypeOfSalaryBaseDTO();
        dto1.setSalaryName("test1");
        dto1.setSalaryCode("102");
        dto1.setPaymentPeriodicityId(1L);
        service.add1(dto1);

    }
    @Test
    @Transactional
    @Commit
    public void getListTest() throws Exception{

        Pageable paging = PageRequest.of(0,5);
        Page<TypeOfSalaryEntity> entities= repository.findAll( paging);
        System.out.println(entities);

        ResponseEntity<?> listResponse =  service.get(0,5);

        PageableResponse<TypeOfSalaryDTO> response = (PageableResponse<TypeOfSalaryDTO>) listResponse.getBody();

        List<TypeOfSalaryDTO> list = (List<TypeOfSalaryDTO>) response.getPages() ;
        assertEquals(2,list.size());

    }

}
