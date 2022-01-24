package az.gov.mia.grp.test.api;

import az.gov.mia.grp.test.model.EchoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Rasim R. İmanov
 */
@Validated
@Tag(description = "the test API", name = "test")
public interface TestApi {
    
    default TestApiDelegate getDelegate(){
        return new TestApiDelegate(){};
    }
    
    @Operation(description = "Test purpose echo call", tags = { "Test echo", "test", "echo"},
            summary = "test echo")
    @GetMapping("/test/echo")
    default ResponseEntity<String> echo(){
        
        return getDelegate().echo();
    }
    
    @GetMapping("test/echo/all")
    default ResponseEntity<Page<EchoDto>> getAllEchos(Pageable page){
        return getDelegate().getAllEchos(page);
    }
}
