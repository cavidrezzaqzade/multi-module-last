package az.gov.mia.grp.api;

import az.gov.mia.grp.test.model.EchoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Rasim R. İmanov
 */
public interface TestApiDelegate {

    default ResponseEntity<String> echo() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    default ResponseEntity<Page<EchoDto>> getAllEchos(Pageable page){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
