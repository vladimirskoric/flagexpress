package ph.devcon.flag.core.component.auth.application;

import ph.devcon.flag.core.component.auth.domain.AccessCodes;
import ph.devcon.flag.core.component.auth.domain.AuthenticationException;
import ph.devcon.flag.core.component.auth.domain.IamProviderException;

public interface AuthService {

    AccessCodes login(String username, String password) throws AuthenticationException, IamProviderException;

    AccessCodes generateClientToken(String clientID, String clientSecret) throws AuthenticationException, IamProviderException;

}
