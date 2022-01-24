package az.gov.mia.grps.commons.swagger;

import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ResolvedTypes;
import springfox.documentation.schema.TypeNameExtractor;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.contexts.ModelContext;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spi.service.contexts.ParameterContext;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import az.gov.mia.grps.commons.specification.And;
import az.gov.mia.grps.commons.specification.Conjunction;
import az.gov.mia.grps.commons.specification.Disjunction;
import az.gov.mia.grps.commons.specification.Or;
import az.gov.mia.grps.commons.specification.Spec;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import springfox.documentation.schema.JacksonEnumTypeDeterminer;
import springfox.documentation.spi.schema.EnumTypeDeterminer;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class SwaggerSpecificationParameterBuilderPlugin implements OperationBuilderPlugin {

    private final TypeNameExtractor nameExtractor;
    private final TypeResolver resolver;
    private final ResolvedType specificationType;

    private final List<Class<? extends Annotation>> annotationTypes = Arrays.asList(Spec.class, Or.class, And.class, Conjunction.class, Disjunction.class);

    @Autowired
    public SwaggerSpecificationParameterBuilderPlugin(TypeNameExtractor nameExtractor, TypeResolver resolver) {
        this.nameExtractor = nameExtractor;
        this.resolver = resolver;
        this.specificationType = resolver.resolve(Specification.class);
    }

    @Override
    public void apply(OperationContext context) {
        
        List<ResolvedMethodParameter> methodParameters = context.getParameters();
        List<Parameter> parameters = new ArrayList<>();

        int paramIdx = 0;
        for (ResolvedMethodParameter methodParameter : methodParameters) {
            ResolvedType resolvedType = methodParameter.getParameterType();
            
            if (supportsParameter(methodParameter)) {
            
                ParameterContext parameterContext = new ParameterContext(methodParameter,
//                        new ParameterBuilder(),
                        context.getDocumentationContext(),
                        context.getGenericsNamingStrategy(),
                        context, paramIdx++);

                Function<ResolvedType, ? extends ModelReference> factory = createModelRefFactory(parameterContext);

                ModelReference intModel = factory.apply(resolver.resolve(Integer.TYPE));
                ModelReference stringMultiModel = factory.apply(resolver.resolve(List.class, String.class));
                ModelReference stringModel = factory.apply(resolver.resolve(String.class));

                List<Spec> specList = getSpecAnnotations(methodParameter);
                for (Spec spec : specList) {

                    if (spec.constVal().length > 0) {

                        continue;
                    }

                    if (spec.params().length > 0) {
                        for (String paramName : spec.params()) {
                            parameters.add(new ParameterBuilder()
                                    .parameterType("query")
                                    .name(paramName)
                                    .modelRef(stringModel) //TODO
                                    .description("" + StringUtils.join(spec.config(), ",")).build());
                        }
                    } else {

                        parameters.add(new ParameterBuilder()
                                .parameterType("query")
                                .name(spec.path())
                                .modelRef(stringModel) //TODO
                                .description("" + StringUtils.join(spec.config(), ",")).build());
                    }
                }

                context.operationBuilder().parameters(parameters);
            }
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    private Function<ResolvedType, ? extends ModelReference> createModelRefFactory(ParameterContext context) {

        ModelContext modelContext = ModelContext.inputParam(
                
                context.getOperationContext().getName(),
                context.getGroupName(),
                context.resolvedMethodParameter().getParameterType(),
                Optional.empty(),
                Collections.emptySet(),
                context.getDocumentationType(),
                context.getAlternateTypeProvider(),
                context.getGenericNamingStrategy(),
                context.getIgnorableParameterTypes());
        EnumTypeDeterminer etd = new JacksonEnumTypeDeterminer();
        return ResolvedTypes.modelRefFactory(modelContext, etd, nameExtractor);
    }

    private List<Spec> getSpecAnnotations(ResolvedMethodParameter methodParameter) {
        List<Annotation> annotList = methodParameter.getAnnotations();
        List<Spec> specList = new ArrayList<>();
        for (Annotation annot : annotList) {
            if (annot instanceof Spec) {
                specList.add((Spec) annot);
                return specList;
            } else if (annot instanceof And) {
                And ands = (And) annot;
                specList.addAll(Arrays.asList(ands.value()));
            } else if (annot instanceof Or) {
                Or ors = (Or) annot;
                specList.addAll(Arrays.asList(ors.value()));
            } else if (annot instanceof Conjunction) {
                Conjunction conj = (Conjunction) annot;
                for (Or ors : conj.value()) {
                    specList.addAll(Arrays.asList(ors.value()));
                }
                specList.addAll(Arrays.asList(conj.and()));
            } else if (annot instanceof Disjunction) {
                Disjunction disj = (Disjunction) annot;
                for (And ands : disj.value()) {
                    specList.addAll(Arrays.asList(ands.value()));
                }
                specList.addAll(Arrays.asList(disj.or()));
            }
        }

        return specList;
    }

    private boolean supportsParameter(ResolvedMethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType().getErasedType();

        return paramType.isInterface() && Specification.class.isAssignableFrom(paramType) && isAnnotated(parameter);
    }

    private final boolean isAnnotated(ResolvedMethodParameter target) {
        return getAnnotation(target.getAnnotations()) != null;
    }

    private Object getAnnotation(List<Annotation> parameterAnnotations) {
        for (Annotation annotation : parameterAnnotations) {
            for (Class<? extends Annotation> annotationType : annotationTypes) {
                if (annotationType.isAssignableFrom(annotation.getClass())) {
                    return annotation;
                }
            }
        }
        return null;
    }

    private final Object getAnnotation(Class<?> target) {
        for (Class<? extends Annotation> annotationType : annotationTypes) {
            Annotation potentialAnnotation = target.getAnnotation(annotationType);
            if (potentialAnnotation != null) {
                return potentialAnnotation;
            }
        }
        return null;
    }
}
