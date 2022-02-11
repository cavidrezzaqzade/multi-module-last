package az.gov.mia.grps.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Cavid Aslanov
 * @time 22/02/2021 - 10:19 AM
 **/
@RestController
public class JWKSetRestController {

    @Autowired
    private JWKSet jwkSet;

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> keys() {
        System.out.println("1111111111111111111111111111111");
        return this.jwkSet.toJSONObject();
    }
}
