package az.gov.mia.grps.auth.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import javax.naming.AuthenticationException;



public class MyWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
//        if (e instanceof InternalAuthenticationServiceException){
//            InternalAuthenticationServiceException internalAuthenticationServiceException=(InternalAuthenticationServiceException) e;
//            return ResponseEntity.status(200).body(new CustomOauthException("Istifadeci tapilmadi"));
//        }
        if (e instanceof InvalidGrantException){
            InvalidGrantException invalidGrantException= (InvalidGrantException) e;
            return ResponseEntity
                    .status(invalidGrantException.getHttpErrorCode() )
                    .body(new CustomOauthException(e.getMessage()));
        }


        if (e instanceof OAuth2Exception) {
            OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
            return ResponseEntity
                    .status(oAuth2Exception.getHttpErrorCode())
                    .body(new CustomOauthException(oAuth2Exception.getMessage()));
        }else if(e instanceof AuthenticationException){
            AuthenticationException authenticationException = (AuthenticationException) e;
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new CustomOauthException(authenticationException.getMessage()));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new CustomOauthException(e.getMessage()));
    }
}
