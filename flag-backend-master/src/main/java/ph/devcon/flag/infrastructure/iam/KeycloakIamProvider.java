package ph.devcon.flag.infrastructure.iam;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import ph.devcon.flag.core.component.auth.domain.AccessCodes;
import ph.devcon.flag.core.component.auth.domain.AuthenticationException;
import ph.devcon.flag.core.component.auth.domain.IamProviderException;
import ph.devcon.flag.core.port.auth.IamProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Form;

/**
 * Concrete implementation of an IamProvider using Keycloak.
 */
@ApplicationScoped
public class KeycloakIamProvider implements IamProvider {

    private static final String KEY_CLIENT_ID = "client_id";
    private static final String KEY_CLIENT_SECRET = "client_secret";
    private static final String KEY_GRANT_TYPE = "grant_type";
    private static final String KEY_SCOPE = "scope";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";


    @Inject
    @RestClient
    KeycloakApi keycloakApi;

    @ConfigProperty(name = "keycloak.client_id")
    String clientId;

    @ConfigProperty(name = "keycloak.client_secret")
    String clientSecret;

    @ConfigProperty(name = "keycloak.grant_type")
    String grantType;

    @ConfigProperty(name = "app.oauth2.key_cloak.grant_type")
    String oauth2GrantType;

    @ConfigProperty(name = "keycloak.scope")
    String scope;

    /**
     * Call the Keycloak API to request for a token with the provided username and password.
     *
     * @param username the username for authentication
     * @param password the password for authentication
     * @return AccessCodes
     * @throws AuthenticationException if the username/password does not match
     * @throws IamProviderException if there are issues connecting to the Keycloak Server
     */
    @Override
    public AccessCodes login(final String username, final String password) throws AuthenticationException, IamProviderException {

        Form form = new Form();
        form.param(KEY_CLIENT_ID, this.clientId);
        form.param(KEY_CLIENT_SECRET, this.clientSecret);
        form.param(KEY_GRANT_TYPE, this.grantType);
        form.param(KEY_SCOPE, this.scope);
        form.param(KEY_USERNAME, username);
        form.param(KEY_PASSWORD, password);

        TokenResponse login;
        try {
            login = this.keycloakApi.login(form);
            final AccessCodes iamLoginResponse = AccessCodes
                    .builder()
                    .accessToken(login.getAccessToken())
                    .refreshToken(login.getRefreshToken())
                    .build();
            return iamLoginResponse;
        } catch (final IamProviderException | AuthenticationException e) {
            // log then throw
            throw e;
        } catch (final RuntimeException e) {
            // connection issue, log the convert
            throw new IamProviderException("keycloak is not accessible");
        }
    }

    @Override
    public AccessCodes generateClientToken(final String clientID, final String clientSecret) throws AuthenticationException, IamProviderException {

        Form form = new Form();
        form.param(KEY_CLIENT_ID, clientID);
        form.param(KEY_CLIENT_SECRET, clientSecret);
        form.param(KEY_GRANT_TYPE, this.oauth2GrantType);

        TokenResponse login;
        try {
            login = this.keycloakApi.login(form);
            return AccessCodes
                    .builder()
                    .accessToken(login.getAccessToken())
                    .refreshToken(login.getRefreshToken())
                    .build();
        } catch (final IamProviderException | AuthenticationException e) {
            // log then throw
            throw e;
        } catch (final RuntimeException e) {
            // connection issue, log the convert
            throw new IamProviderException("keycloak is not accessible");
        }
    }

}
