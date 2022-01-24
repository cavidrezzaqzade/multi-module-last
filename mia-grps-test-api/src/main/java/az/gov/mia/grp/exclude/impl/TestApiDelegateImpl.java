package az.gov.mia.grp.exclude.impl;

import az.gov.mia.grp.test.api.TestApiDelegate;
import az.gov.mia.grp.test.model.EchoDto;
import java.util.Arrays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rasim R. İmanov
 */
@Service
public class TestApiDelegateImpl implements TestApiDelegate {

    @Override
    public ResponseEntity<String> echo() {
        return new ResponseEntity<>("Hello echo", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<EchoDto>> getAllEchos(Pageable page) {
        return ResponseEntity.ok(new PageImpl<>(
                Arrays.asList(new EchoDto("message1"), new EchoDto("message2"), new EchoDto("message3")), page, 3)
        );
    }

}
