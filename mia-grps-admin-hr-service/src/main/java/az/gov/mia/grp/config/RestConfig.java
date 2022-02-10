package az.gov.mia.grp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.TimeZone;

/**
 * @author Cavid Aslanov
 * @time 15/02/2021 - 11:13 AM
 **/
@Configuration
public class RestConfig {

    @Autowired
    public void configureJackson(ObjectMapper objectMapper) {
        objectMapper.setTimeZone(TimeZone.getDefault());
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public static RestTemplate restTemplate() {
        RestTemplate dlRestTemplate = new RestTemplate();

        dlRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        dlRestTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                HttpStatus statusCode = response.getStatusCode();
                return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
            }
        });

        dlRestTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("grps-default","grp$!23"));

        return dlRestTemplate;
    }

}
