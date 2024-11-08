package ph.devcon.flag.entrypoint.api.rest.sms;

import lombok.extern.slf4j.Slf4j;
import ph.devcon.flag.core.component.sms.application.SmsService;
import ph.devcon.flag.core.component.sms.domain.SmsConfirmation;
import ph.devcon.flag.core.component.sms.domain.SmsProviderException;
import ph.devcon.flag.core.component.sms.domain.SmsServiceException;
import ph.devcon.flag.entrypoint.api.rest.error.JsonError;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_INTERNAL_SERVER_ERROR;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_NOT_FOUND;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_SERVICE_UNAVAILABLE;


/**
 * The sms resource handles SMS requests.
 */
@Slf4j
@Path("/sms/message")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@ApplicationScoped
public class SmsMessageResource {

    private final SmsService smsService;


    /**
     * The SMS resources calls the smsService for the actual SMS.
     *
     * @param smsService the service responsible for SMS.
     */
    public SmsMessageResource(final SmsService smsService) {
        this.smsService = smsService;
    }


    /**
     * The sendMessage endpoint. Takes a {@link SendSmsMessageRequest} and returns a {@link SendSmsMessageResponse} or a {@link JsonError} if
     * there are issues during the SMS process.
     *
     * @param request the sendMessage request containing the username and password
     * @return SmsRequest or JsonError
     */
    @Path("")
    @POST
    public Response sendMessage(final SendSmsMessageRequest request) {
        Response response;
        try {
            final SmsConfirmation smsConfirmation = this.smsService.sendMessage(
                    request.getNumber(), // TODO validate format
                    request.getMessage() // TODO validate length (and content?)
            );
            SendSmsMessageResponse smr = SendSmsMessageResponse
                    .builder()
                    .messageId(smsConfirmation.getFlagMessageId())
                    .status(smsConfirmation.getStatus())
                    .build();
            response = Response.ok(smr).build();
        } catch (SmsProviderException e) {
            final JsonError error = JsonError.simple(
                    "503", // TODO custom error code?
                    "Service Unavailable",
                    e.getMessage());
            response = Response.status(SC_SERVICE_UNAVAILABLE).entity(error).build();
        } catch (Exception e) {
            log.error("error occurred when sending", e);
            final JsonError error = JsonError.simple(
                    "500", // TODO custom error code?
                    "Internal Server Error",
                    "Please contact the development team.");
            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }
        return response;
    }

    @GET
    @Path("/{id}/status")
    public Response getMessageStatus(@PathParam("id") final String id) {
        Response response;
        try {
            final SmsConfirmation smsConfirmation = this.smsService.retrieveMessageStatus(id);
            SendSmsMessageResponse smr = SendSmsMessageResponse
                    .builder()
                    .messageId(smsConfirmation.getFlagMessageId())
                    .status(smsConfirmation.getStatus())
                    .build();
            response = Response.ok(smr).build();
        } catch (SmsServiceException e) {
            JsonError error = JsonError.builder()
                    .status("404") // TODO custom error code?
                    .title("Message Not Found")
                    .detail(e.getMessage())
                    .build();
            response = Response.status(SC_NOT_FOUND).entity(error).build();
        } catch (Exception e) {
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder()
                    .status("500") // TODO custom error code?
                    .title("An unknown error has occurred")
                    .detail("Please contact the development team.")
                    .build();
            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }
        return response;
    }

}
