package ph.devcon.flag.core.component.auth.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ph.devcon.flag.core.component.auth.domain.AccessCodes;
import ph.devcon.flag.core.component.auth.domain.AuthenticationException;
import ph.devcon.flag.core.component.auth.domain.IamProviderException;
import ph.devcon.flag.core.port.auth.IamProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DefaultAuthServiceTest {

    AuthService authService;
    IamProvider mockIamProvider;

    @BeforeEach
    void before() {
        this.mockIamProvider = Mockito.mock(IamProvider.class);
        this.authService = new DefaultAuthService(this.mockIamProvider);
    }

    @Test
    void loginSuccess() {
        String accessToken = "test.access.token";
        String refreshToken = "test.refresh.token";
        AccessCodes accessCodes = AccessCodes.builder().accessToken(accessToken).refreshToken(refreshToken).build();
        when(this.mockIamProvider.login(any(), any())).thenReturn(accessCodes);

        try {
            AccessCodes codes = this.authService.login("test", "test");
            assertNotNull(codes);
            assertEquals(accessToken, codes.getAccessToken());
            assertEquals(refreshToken, codes.getRefreshToken());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void loginFail() {
        when(this.mockIamProvider.login(any(), any())).thenThrow(new AuthenticationException("auth failed"));

        try {
            this.authService.login("test", "test");
            fail("should not reach this part");
        } catch (AuthenticationException e) {
            assertNotNull(e);
            return;
        }
        fail("should not reach this part");
    }

    @Test
    void loginInaccessible() {
        when(this.mockIamProvider.login(any(), any())).thenThrow(new IamProviderException("conn failed"));

        try {
            this.authService.login("test", "test");
            fail("should not reach this part");
        } catch (IamProviderException e) {
            assertNotNull(e);
            return;
        }
        fail("should not reach this part");
    }

}