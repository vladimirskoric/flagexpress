package ph.devcon.flag.core.port.auth;

import ph.devcon.flag.core.component.auth.domain.AccessCodes;
import ph.devcon.flag.core.component.auth.domain.AuthenticationException;
import ph.devcon.flag.core.component.auth.domain.IamProviderException;

/**
 * This is a driven port for IAM Provider. eg. Keycloak
 */
public interface IamProvider {

    AccessCodes login(String username, String password) throws AuthenticationException, IamProviderException;

    AccessCodes generateClientToken(String clientID, String clientSecret) throws AuthenticationException, IamProviderException;

}
