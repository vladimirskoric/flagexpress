package ph.devcon.flag.infrastructure.iam;


import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Form;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


/**
 * A MicroProfile definition for the Keycloak API.
 */
@RegisterRestClient(configKey = "keycloak-api")
@RegisterProvider(value = KeycloakResponseMapper.class)
public interface KeycloakApi {

    @POST
    @Path("/realms/flag/protocol/openid-connect/token")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @Produces(APPLICATION_JSON)
    TokenResponse login(Form form);

}
