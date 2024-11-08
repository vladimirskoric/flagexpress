package ph.devcon.flag.entrypoint.api.rest.requestor;

import lombok.extern.slf4j.Slf4j;
import ph.devcon.flag.core.component.requestor.application.RequestorService;
import ph.devcon.flag.core.component.requestor.domain.Requestor;
import ph.devcon.flag.entrypoint.api.rest.error.JsonError;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_INTERNAL_SERVER_ERROR;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_NOT_FOUND;

@Slf4j
@Path("/requestors")
@Produces(APPLICATION_JSON)
@ApplicationScoped
public class RequestorResource {

    @Inject
    RequestorService requestorService;

    @GET
    @Path("/{id}")
    public Response getRequestor(@PathParam("id") final int id) {
        Response response;
        try {
            Requestor requestor = requestorService.getRequestor(id);
            if (requestor != null) {
                GetRequestorResponse requestorResponse = GetRequestorResponse.builder()
                        .id(requestor.getId())
                        .firstName(requestor.getFirstName())
                        .lastName(requestor.getLastName())
                        .mobileNumber(requestor.getMobileNumber())
                        .type(requestor.getRequestorType().getKey())
                        .group(requestor.getGroup())
                        .email(requestor.getEmail())
                        .status(requestor.getRequestorStatus().getKey())
                        .build();
                response = Response.ok(requestorResponse).build();
            } else {
                JsonError error = JsonError.builder()
                        .status("404")
                        .title("Requestor Not Found")
                        .detail("The requestor selected was not found")
                        .build();
                response = Response.status(SC_NOT_FOUND).entity(error).build();
            }
        } catch (Exception e) {
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder()
                    .status("500")
                    .title("An Unknown Error has occurred")
                    .detail("TODO: FIX THIS")
                    .build();
            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }
        return response;
    }
}
