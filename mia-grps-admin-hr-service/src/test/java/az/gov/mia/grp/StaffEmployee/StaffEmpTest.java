//package az.gov.mia.grp.StaffEmployee;
//
//import az.gov.mia.grp.MiaGrpsAdminHrServiceApplication;
//import az.gov.mia.grp.entity.StaffEmployee.StaffEmpEntity;
//import az.gov.mia.grp.model.StaffEmployee.StaffEmployeeDTO;
//import az.gov.mia.grp.repository.StaffEmployee.StaffEmpsRepository;
//import az.gov.mia.grp.service.StaffEmployee.StaffEmpsService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static junit.framework.TestCase.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@ContextConfiguration
//@SpringBootTest(classes = MiaGrpsAdminHrServiceApplication.class)
// class StaffEmpTest {
//    @Autowired
//    private StaffEmpsService service;
//
//    @Autowired
//    private StaffEmpsRepository repo;
//    @Test
//    void testService(){
//        assertNotNull(service);
//    }
//    @Test
//    void testGetMethod(){
//        List<Long> ids = new ArrayList<>();
//        ids.add(Long.valueOf("81"));
//        ids.add(Long.valueOf("82"));
//        ids.add(Long.valueOf("75"));
//        ResponseEntity e =  service.getStaffEmployees(ids);
//        List<StaffEmployeeDTO> dtos  = (List<StaffEmployeeDTO>) e.getBody();
//        assertEquals(3,dtos.size());
//    }
//
//    @Test
//    void testRepo(){
//        List<Integer> ids = new ArrayList<>();
//        ids.add(81);
//        ids.add(82);
//        List<StaffEmpEntity> e =  repo.getStaffEmployes_(ids);
////        List<StaffEmployeeDTO> dtos  = (List<StaffEmployeeDTO>) e.getBody();
//        assertEquals(2,e.size());
//
//    }
//}
