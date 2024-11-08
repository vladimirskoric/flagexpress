package ph.devcon.flag.infrastructure.iam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ph.devcon.flag.core.component.auth.domain.AccessCodes;
import ph.devcon.flag.core.component.auth.domain.AuthenticationException;
import ph.devcon.flag.core.component.auth.domain.IamProviderException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KeycloakIamProviderTest {

    private KeycloakApi mockKeycloakApi;
    private KeycloakIamProvider iamProvider;

    @BeforeEach
    void before() {
        this.mockKeycloakApi = mock(KeycloakApi.class);
        this.iamProvider = new KeycloakIamProvider();
        this.iamProvider.keycloakApi = this.mockKeycloakApi;
    }

    @Test
    void testLoginSuccess() {
        // setup
        String actualAccessToken = "test.access.token";
        String actualRefreshToken = "test.refresh.token";
        TokenResponse validResponse = TokenResponse.builder()
                .accessToken(actualAccessToken)
                .refreshToken(actualRefreshToken)
                .build();
        when(this.mockKeycloakApi.login(any())).thenReturn(validResponse);

        // actual call
        this.iamProvider.keycloakApi = this.mockKeycloakApi;
        AccessCodes accessCodes = this.iamProvider.login("username", "oassword");

        // assertions
        assertNotNull(accessCodes);
        assertEquals(actualAccessToken, accessCodes.getAccessToken());
        assertEquals(actualRefreshToken, accessCodes.getRefreshToken());
    }

    @Test
    void testLoginFail() {
        // setup
        when(this.mockKeycloakApi.login(any())).thenThrow(new AuthenticationException("auth failed"));

        // actual call
        this.iamProvider.keycloakApi = this.mockKeycloakApi;
        try {
            this.iamProvider.login("username", "oassword");
        } catch (AuthenticationException e) {
            assertNotNull(e);
            return;
        }

        Assertions.fail("should have thrown an exception");
    }

    @Test
    void testLoginInaccessible() {
        // setup
        when(this.mockKeycloakApi.login(any())).thenThrow(new RuntimeException());

        // actual call
        this.iamProvider.keycloakApi = this.mockKeycloakApi;
        try {
            this.iamProvider.login("username", "oassword");
        } catch (IamProviderException e) {
            assertNotNull(e);
            return;
        }

        Assertions.fail("should have thrown an exception");
    }
}