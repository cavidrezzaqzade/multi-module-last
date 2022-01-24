package az.gov.mia.grp.test.api;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rasim R. İmanov
 */
@RestController
@RequestMapping("/")
public class TestApiController implements TestApi {

    private final TestApiDelegate delegate;

    public TestApiController(TestApiDelegate delegate) {
        this.delegate = delegate;
    }
    //    public TestApiController(@Autowired(required = false) TestApiDelegate delegate) {
//        this.delegate = Optional.ofNullable(delegate).orElse(new TestApiDelegate() {
//        });
//    }

    @Override
    public TestApiDelegate getDelegate() {
        return delegate;
    }

}
