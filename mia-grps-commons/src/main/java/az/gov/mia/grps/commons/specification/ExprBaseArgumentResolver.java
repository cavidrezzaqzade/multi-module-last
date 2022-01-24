package az.gov.mia.grps.commons.specification;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 *
 * @author Rasim R. Imanov
 */
public class ExprBaseArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter mp) {
        return mp.getParameterType().equals(SearchExpr.class);
    }

    @Override
    public Object resolveArgument(MethodParameter mp,
            ModelAndViewContainer mavc,
            NativeWebRequest nwr,
            WebDataBinderFactory wdbf) throws Exception {
        return null;
    }

}
