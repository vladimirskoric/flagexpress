package ph.devcon.flag.core.component.auth.application;

import ph.devcon.flag.core.component.auth.domain.AccessCodes;
import ph.devcon.flag.core.component.auth.domain.AuthenticationException;
import ph.devcon.flag.core.component.auth.domain.IamProviderException;
import ph.devcon.flag.core.port.auth.IamProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DefaultAuthService implements AuthService {

    private final IamProvider iamProvider;

    @Inject
    DefaultAuthService(final IamProvider iamProvider) {
        this.iamProvider = iamProvider;
    }

    @Override
    public AccessCodes login(final String username, final String password) throws AuthenticationException, IamProviderException {
        return this.iamProvider.login(username, password);
    }

    @Override
    public AccessCodes generateClientToken(final String clientID, final String clientSecret) throws AuthenticationException, IamProviderException {
        return this.iamProvider.generateClientToken(clientID, clientSecret);
    }

}
