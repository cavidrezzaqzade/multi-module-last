package az.gov.mia.grps.commons.swagger;

import io.swagger.v3.oas.models.security.SecurityScheme.In;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *
 * @author Rasim R. İmanov
 */
public abstract class AbstractSwaggerConfig {

    @Bean
    public Docket api() {

        
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())                
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContexts()));
    }
    
    
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("GRP API")
                .description("GRP API Documentation")
                .version("1.0")
                .build();
    }

    private SecurityContext securityContexts() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(basicAuthReference()))
                .forPaths(PathSelectors.any())
                .build();
    }

    private SecurityScheme securityScheme() {
        return new ApiKey("Token Access", HttpHeaders.AUTHORIZATION, In.HEADER.name());
    }

    private SecurityReference basicAuthReference() {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }
}
