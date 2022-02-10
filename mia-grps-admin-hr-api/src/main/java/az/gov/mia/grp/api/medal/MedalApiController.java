package az.gov.mia.grp.api.medal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/adminhr/medals")
public class MedalApiController implements MedalApi {
    private final MedalApiDelegate delegate;

    public MedalApiController(@Autowired(required = false) MedalApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new MedalApiDelegate() {});
    }

    @Override
    public MedalApiDelegate getDelegate() {
        return delegate;
    }
}