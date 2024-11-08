package ph.devcon.flag.entrypoint.api.rest.auth;

import lombok.extern.slf4j.Slf4j;
import ph.devcon.flag.core.component.auth.application.AuthService;
import ph.devcon.flag.core.component.auth.domain.AccessCodes;
import ph.devcon.flag.core.component.auth.domain.AuthenticationException;
import ph.devcon.flag.core.component.auth.domain.IamProviderException;
import ph.devcon.flag.entrypoint.api.rest.error.JsonError;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_BAD_GATEWAY;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_UNAUTHORIZED;


/**
 * The auth resource handles authentication requests.
 */
@Slf4j
@Path("/auth")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@ApplicationScoped
public class AuthResource {

    private final AuthService authService;


    /**
     * The auth resources calls the authService for the actual authentication.
     *
     * @param authService the service responsible for authentication.
     */
    public AuthResource(final AuthService authService) {
        this.authService = authService;
    }


    /**
     * The login endpoint. Takes a {@link LoginRequest} and returns a {@link LoginResponse} or a {@link JsonError} if
     * there are issues during the authentication process.
     *
     * @param request the login request containing the username and password
     * @return LoginRequest or JsonError
     */
    @Path("/login")
    @POST
    public Response login(final LoginRequest request) {
        Response response;
        try {
            final AccessCodes accessCodes = this.authService.login(request.getUsername(), request.getPassword());
            LoginResponse lr = LoginResponse
                    .builder()
                    .accessCode(accessCodes.getAccessToken())
                    .refreshCode(accessCodes.getRefreshToken())
                    .build();
            response = Response.ok(lr).build();
        } catch (AuthenticationException e) {
            final JsonError error = JsonError.simple(
                    "401",
                    "Unauthorized",
                    e.getMessage());
            response = Response.status(SC_UNAUTHORIZED).entity(error).build();
        } catch (IamProviderException e) {
            final JsonError error = JsonError.simple(
                    "502",
                    "Bad Gateway",
                    e.getMessage());
            response = Response.status(SC_BAD_GATEWAY).entity(error).build();
        } catch (Exception e) {
            final JsonError error = JsonError.simple(
                    "500",
                    "Internal Server Error",
                    e.getMessage());
            response = Response.status(SC_BAD_GATEWAY).entity(error).build();
        }
        return response;
    }

    @Path("/client/token")
    @POST
    public Response generateClientToken(@Valid final ClientTokenRequest tokenRequest) {
        try {
            final AccessCodes accessCodes = this.authService.generateClientToken(tokenRequest.getClientID(), tokenRequest.getClientSecret());
            return Response.ok(ClientTokenResponse
                    .builder()
                    .accessToken(accessCodes.getAccessToken())
                    .build())
                    .build();
        } catch (AuthenticationException e) {// TODO refactor common codes in another task with regression tests
            return Response.status(SC_UNAUTHORIZED).entity(
                    JsonError.simple(
                    "401",
                    "Unauthorized",
                    e.getMessage())).build();
        } catch (IamProviderException e) {
            return Response.status(SC_BAD_GATEWAY).entity(
                    JsonError.simple(
                    "502",
                    "Bad Gateway",
                    e.getMessage())).build();
        } catch (Exception e) {
            return Response.status(SC_BAD_GATEWAY).entity(
                    JsonError.simple(
                    "500",
                    "Internal Server Error",
                    e.getMessage())).build();
        }
    }

}
