package az.gov.mia.grp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 *
 * @author Rasim R. İmanov
 */
@RestController
@RequestMapping("/admin")
public class TestApiController implements TestApi {

    private final TestApiDelegate delegate;

    public TestApiController(@Autowired(required = false) TestApiDelegate delegate) {
                this.delegate = Optional.ofNullable(delegate).orElse(new TestApiDelegate() {});
     }

    @Override
    public TestApiDelegate getDelegate() {
        return delegate;
    }

}
