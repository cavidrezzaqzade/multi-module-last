package az.gov.mia.grps.service.impl;

import az.gov.mia.grp.api.TestApiDelegate;
import az.gov.mia.grp.test.model.EchoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Rasim R. İmanov
 */
public class TestApiDelegateImpl implements TestApiDelegate{

    @Override
    public ResponseEntity<String> echo() {
        return TestApiDelegate.super.echo(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseEntity<Page<EchoDto>> getAllEchos(Pageable page) {
        return TestApiDelegate.super.getAllEchos(page); //To change body of generated methods, choose Tools | Templates.
    }
 
    
}
