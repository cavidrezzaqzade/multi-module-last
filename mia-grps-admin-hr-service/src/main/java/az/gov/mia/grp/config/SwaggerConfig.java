package az.gov.mia.grp.config;

/**
 * @author Cavid Aslanov
 * @time 07/04/2021 - 12:11 PM
 **/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket swaggerSettings() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(Stream.of(MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_PDF_VALUE,
                        MediaType.APPLICATION_XML_VALUE)
                        //MediaType.APPLICATION_CBOR_VALUE,
                        //MediaType.APPLICATION_XHTML_XML_VALUE
                        .collect(Collectors.toSet()))
                .produces(Stream.of(MediaType.APPLICATION_JSON_VALUE,
                        MediaType.APPLICATION_PDF_VALUE,
                        MediaType.APPLICATION_XML_VALUE)
                        //MediaType.APPLICATION_CBOR_VALUE,
                        //MediaType.APPLICATION_XHTML_XML_VALUE
                        .collect(Collectors.toSet()))

                .select()
                .apis(RequestHandlerSelectors.basePackage("az.gov.mia.grp"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/swagger-ui.html**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/api/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("MINISTRY OF INTERNAL AFFAIRS GRP", "MIA GRP", "API", "Terms of service",
                new Contact("IKTEX", "https://www.linkedin.com/company/ik-tex-mmc", "email@email.com"), "License of API", "API license URL",
                Collections.emptyList());
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}
