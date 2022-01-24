/**
 * Copyright 2014-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package az.gov.mia.grps.commons.specification;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import az.gov.mia.grps.commons.specification.model.Conjunction;

import org.springframework.core.MethodParameter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author Tomasz Kaczmarzyk
 */
class JoinFetchSpecificationResolver implements HandlerMethodArgumentResolver {

    private AnnotSpecificationArgumentResolver parentResolver;

    public JoinFetchSpecificationResolver(AnnotSpecificationArgumentResolver parent) {
        this.parentResolver = parent;
    }

    @Override
    public boolean supportsParameter(MethodParameter param) {
        Class<?> paramType = param.getParameterType();
        return paramType.isInterface() && Specification.class.isAssignableFrom(paramType)
                && (isAnnotatedWith(JoinFetch.class, param) || isAnnotatedWith(Joins.class, param));
    }

    private boolean isAnnotatedWith(Class<? extends Annotation> annotation, MethodParameter param) {
        return param.hasParameterAnnotation(annotation) || param.getParameterType().isAnnotationPresent(annotation);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Specification<Object> fetchSpec = resolveFetchSpec(parameter);
        @SuppressWarnings("unchecked")
        Specification<Object> regularSpec = (Specification<Object>) parentResolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory, this);

        Specification<Object> spec = regularSpec == null ? fetchSpec : new Conjunction<Object>(Arrays.asList(fetchSpec, regularSpec));

        if (Specification.class == parameter.getParameterType()) {
            return spec;
        } else {
            return EnhancerUtil.wrapWithIfaceImplementation(parameter.getParameterType(), spec);
        }
    }

    private Specification<Object> resolveFetchSpec(MethodParameter parameter) {
        if (isAnnotatedWith(JoinFetch.class, parameter)) {
            JoinFetch fetchDef = getFetchDef(JoinFetch.class, parameter);
            return newJoinFetch(fetchDef);
        } else if (isAnnotatedWith(Joins.class, parameter)) {
            Joins joinsDef = getFetchDef(Joins.class, parameter);
            Collection<Specification<Object>> joins = new ArrayList<>();
            for (JoinFetch fetchDef : joinsDef.value()) {
                joins.add(newJoinFetch(fetchDef));
            }
            return new Conjunction<Object>(joins);
        } else {
            throw new IllegalArgumentException("either @JoinFetch or @Joins expected!");
        }
    }

    private az.gov.mia.grps.commons.specification.model.JoinFetch<Object> newJoinFetch(JoinFetch fetchDef) {
        return new az.gov.mia.grps.commons.specification.model.JoinFetch<Object>(fetchDef.paths(), fetchDef.joinType());
    }

    private <A extends Annotation> A getFetchDef(Class<A> annotation, MethodParameter parameter) {
        A fetchDef = parameter.getParameterAnnotation(annotation);
        if (fetchDef == null) {
            fetchDef = parameter.getParameterType().getAnnotation(annotation);
        }
        return fetchDef;
    }

}
