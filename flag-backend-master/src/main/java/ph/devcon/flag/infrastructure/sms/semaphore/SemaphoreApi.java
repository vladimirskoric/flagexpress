package ph.devcon.flag.infrastructure.sms.semaphore;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Form;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@RegisterRestClient(configKey = "semaphore-api")
@RegisterProvider(value = SemaphoreResponseExceptionMapper.class)
public interface SemaphoreApi {

    @POST
    @Path("/messages")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @Produces(APPLICATION_JSON)
    SemaphoreMessageResponse[] sendMessage(Form form);

    @GET
    @Path("/messages/{id}")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @Produces(APPLICATION_JSON)
    SemaphoreMessageResponse[] retrieveMessage(@PathParam("id") String id, @QueryParam("apikey") String apiKey);

    @GET
    @Path("/messages")
    @Consumes(APPLICATION_FORM_URLENCODED)
    @Produces(APPLICATION_JSON)
    SemaphoreMessageResponse[] retrieveMessages(Form form);

}
