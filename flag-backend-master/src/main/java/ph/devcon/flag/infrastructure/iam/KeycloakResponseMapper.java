package ph.devcon.flag.infrastructure.iam;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;
import ph.devcon.flag.core.component.auth.domain.AuthenticationException;
import ph.devcon.flag.core.component.auth.domain.IamProviderException;

import javax.ws.rs.core.Response;


/**
 * Custom ResponseExceptionMapper when calling the API. This is needed since the errors returned by Keycloak is returned
 * to the actual caller. We catch the Exceptions here and manage them properly so we can return a proper JsonError
 * message.
 */
public final class KeycloakResponseMapper implements ResponseExceptionMapper<RuntimeException> {

    public static final int CODE_BAD_RESPONSE = 400;
    public static final int CODE_ACCESS_DENIED = 401;

    @Override
    public RuntimeException toThrowable(final Response response) {
        final int status = response.getStatus();
        switch (status) {
            case KeycloakResponseMapper.CODE_BAD_RESPONSE:
                return new IamProviderException("request parameter for token authentication is malformed");
            case KeycloakResponseMapper.CODE_ACCESS_DENIED:
                return new AuthenticationException("client_secret, username, and/or password is incorrect");
            default:
                return new IamProviderException("an undefined keycloak exception has been caught. This needs to be fixed");
        }
    }

}
