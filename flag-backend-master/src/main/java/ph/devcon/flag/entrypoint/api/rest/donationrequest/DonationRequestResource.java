package ph.devcon.flag.entrypoint.api.rest.donationrequest;

import lombok.extern.slf4j.Slf4j;
import ph.devcon.flag.core.component.donationrequest.application.DonationRequestDTO;
import ph.devcon.flag.core.component.donationrequest.application.DonationRequestService;
import ph.devcon.flag.core.component.exception.InvalidRequestException;
import ph.devcon.flag.entrypoint.api.rest.error.JsonError;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jboss.resteasy.spi.HttpResponseCodes.*;

@Slf4j
@Path("/donationrequests")
@Produces(APPLICATION_JSON)
@ApplicationScoped
public class DonationRequestResource {

    @Inject
    DonationRequestService donationRequestService;

    @POST
    @Path("/")
    public Response createDonationRequest(DonationRequestDTO request) throws FileNotFoundException {
        Response response = Response.status(SC_CREATED).build();
        try {
            response = Response
                    .ok(donationRequestService.createUpdateDonationRequest(request))
                    .build();
            log.info("Request: " + request.toString());
        } catch (InvalidRequestException e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("400").title("Invalid Request").detail(e.getMessage())
                    .build();
            response = Response.status(SC_BAD_REQUEST).entity(error).build();
        } catch (Exception e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("500").title("An Unknown Error has occurred")
                    .detail(e.getMessage()).build();

            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }

        return response;
    }

    @GET
    @Path("/")
    public Response getDonationRequest() throws FileNotFoundException {
        Response response = null;
        try {
            List<DonationRequestDTO> donationRequests = donationRequestService.getDonationRequests();
            response = Response.ok(donationRequests).build();
        } catch (InvalidRequestException e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("400").title("Invalid Request").detail(e.getMessage())
                    .build();
            response = Response.status(SC_BAD_REQUEST).entity(error).build();
        } catch (Exception e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("500").title("An Unknown Error has occurred")
                    .detail(e.getMessage()).build();

            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }

        return response;
    }

    
    @GET
    @Path("/{id}")
    public Response getDonationRequestById(@PathParam("id") final long id) throws FileNotFoundException {
        Response response = null;
        try {
            DonationRequestDTO donationRequest = donationRequestService.getDonationRequestById(id);
            response = Response.ok(donationRequest).build();
        } catch (InvalidRequestException e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("400").title("Invalid Request").detail(e.getMessage())
                    .build();
            response = Response.status(SC_BAD_REQUEST).entity(error).build();
        } catch (Exception e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("500").title("An Unknown Error has occurred")
                    .detail(e.getMessage()).build();

            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }

        return response;
    }


    @GET
    @Path("/donor/{id}")
    public Response getDonationRequestByDonorId(@PathParam("id") final long donorId) throws FileNotFoundException {
        Response response = null;
        try {
            List<DonationRequestDTO> donationRequests = donationRequestService.getDonationRequestByDonorId(donorId);
            response = Response.ok(donationRequests).build();
        } catch (InvalidRequestException e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("400").title("Invalid Request").detail(e.getMessage())
                    .build();
            response = Response.status(SC_BAD_REQUEST).entity(error).build();
        } catch (Exception e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("500").title("An Unknown Error has occurred")
                    .detail(e.getMessage()).build();

            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }

        return response;
    }


    @DELETE
    @Path("/")
    public Response deleteDonationRequests() throws FileNotFoundException {
        Response response = Response.ok(SC_OK).build();
        try {
            donationRequestService.DeleteDonationRequests();
        } catch (InvalidRequestException e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("400").title("Invalid Request").detail(e.getMessage())
                    .build();
            response = Response.status(SC_BAD_REQUEST).entity(error).build();
        } catch (Exception e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("500").title("An Unknown Error has occurred")
                    .detail(e.getMessage()).build();

            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }

        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDonationRequestById(@PathParam("id") final long id) throws FileNotFoundException {
        Response response = Response.ok(SC_OK).build();
        try {
            donationRequestService.DeleteDonationRequestById(id);
        } catch (InvalidRequestException e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("400").title("Invalid Request").detail(e.getMessage())
                    .build();
            response = Response.status(SC_BAD_REQUEST).entity(error).build();
        } catch (Exception e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("500").title("An Unknown Error has occurred")
                    .detail(e.getMessage()).build();

            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }

        return response;
    }
}
