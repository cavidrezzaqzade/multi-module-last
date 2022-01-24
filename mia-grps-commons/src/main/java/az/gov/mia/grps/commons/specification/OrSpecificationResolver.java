package az.gov.mia.grps.commons.specification;

import java.util.ArrayList;
import java.util.List;

import az.gov.mia.grps.commons.specification.model.Disjunction;

import org.springframework.core.MethodParameter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author Tomasz Kaczmarzyk
 */
class OrSpecificationResolver implements HandlerMethodArgumentResolver {

    private final SimpleSpecificationResolver specResolver;

    public OrSpecificationResolver() {
       specResolver  = new SimpleSpecificationResolver();
    }

    @Override
    public boolean supportsParameter(MethodParameter param) {
        return param.getParameterType() == Specification.class && param.hasParameterAnnotation(Or.class);
    }

    @Override
    public Specification<?> resolveArgument(MethodParameter param, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Or def = param.getParameterAnnotation(Or.class);

        return buildSpecification(webRequest, def);
    }

    Specification<Object> buildSpecification(NativeWebRequest webRequest, Or def) {
        List<Specification<Object>> innerSpecs = new ArrayList<Specification<Object>>();
        for (Spec innerDef : def.value()) {
            Specification<Object> innerSpec = specResolver.buildSpecification(webRequest, innerDef);
            if (innerSpec != null) {
                innerSpecs.add(specResolver.buildSpecification(webRequest, innerDef));
            }
        }

        return innerSpecs.isEmpty() ? null : new Disjunction<Object>(innerSpecs);
    }

}
