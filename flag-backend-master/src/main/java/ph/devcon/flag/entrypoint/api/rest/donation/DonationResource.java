package ph.devcon.flag.entrypoint.api.rest.donation;

import lombok.extern.slf4j.Slf4j;
import ph.devcon.flag.core.component.donations.application.DonationDTO;
import ph.devcon.flag.core.component.donations.application.DonationService;
import ph.devcon.flag.core.component.exception.InvalidRequestException;
import ph.devcon.flag.entrypoint.api.rest.error.JsonError;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_INTERNAL_SERVER_ERROR;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_BAD_REQUEST;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_CREATED;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;


@Slf4j
@Path("/donations")
@Produces(APPLICATION_JSON)
@ApplicationScoped
public class DonationResource {

    @Inject
    public DonationService donationService;

    @POST
    @PUT
    @Path("/")
    public Response createDonation(DonationDTO dto) throws FileNotFoundException {
        Response response = Response.ok(SC_CREATED).build();
        try { 
            donationService.createUpdateDonation(dto);
        } catch (InvalidRequestException e) {
            log.error("error occurred querying by id", e);
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
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
    public Response getDonations() throws FileNotFoundException {
        Response response;
        try {
            List<DonationDTO> donations = donationService.getDonations();
            response = Response.ok(donations).build();
        } catch (Exception e) {
            log.error("error occurred querying by id", e);
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            JsonError error = JsonError.builder()
                    .status("500")
                    .title("An Unknown Error has occurred")
                    .detail("TODO: FIX THIS")
                    .build();

            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }

        return response;
    }

    @GET
    @Path("/{id}")
    public Response getDonation(@PathParam("id") final int id) throws FileNotFoundException {
        Response response;
        try {
            DonationDTO donation = donationService.getDonation(id);
            response = Response.ok(donation).build();
        }  
        catch (InvalidRequestException e) {
            log.error("error occurred querying by id", e);
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            JsonError error = JsonError.builder().status("400").title("Invalid Request").detail(e.getMessage())
                    .build();
            response = Response.status(SC_BAD_REQUEST).entity(error).build();
        }
        catch (Exception e) {
            log.error("error occurred querying by id", e);
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            JsonError error = JsonError.builder()
                    .status("500")
                    .title("An Unknown Error has occurred")
                    .detail("TODO: FIX THIS")
                    .build();

            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }

        return response;
    }

    @GET
    @Path("/request/{id}")
    public Response getDonationByRequestId(@PathParam("id") final int id) throws FileNotFoundException {
        Response response;
        try {
            List<DonationDTO> donations = donationService.getDonationsByRequestId(id);
            response = Response.ok(donations).build();
        }
        catch (InvalidRequestException e) {
            log.error("error occurred querying by id", e);
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            JsonError error = JsonError.builder().status("400").title("Invalid Request").detail(e.getMessage())
                    .build();
            response = Response.status(SC_BAD_REQUEST).entity(error).build();
        } catch (Exception e) {
            log.error("error occurred querying by id", e);
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            JsonError error = JsonError.builder()
                    .status("500")
                    .title("An Unknown Error has occurred")
                    .detail("TODO: FIX THIS")
                    .build();

            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }

        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDonation(@PathParam("id") final int id) throws FileNotFoundException {
        Response response;
        try {
            donationService.deleteDonationById(id);
            response = Response.ok().build();
        } 
        catch (InvalidRequestException e) {
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("400").title("Invalid Request").detail(e.getMessage())
                    .build();
            response = Response.status(SC_BAD_REQUEST).entity(error).build();
        }
        catch (Exception e) {
            log.error("error occurred querying by id", e);
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
            JsonError error = JsonError.builder()
                    .status("500")
                    .title("An Unknown Error has occurred")
                    .detail("TODO: FIX THIS")
                    .build();

            response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
        }

        return response;
    }

    @DELETE
    @Path("/")
    public Response deleteAllDonations() throws FileNotFoundException {
        Response response = Response.ok().build();
        try {
            donationService.deleteAllDonations();
        } catch (Exception e) {
            log.error("error occurred querying by id", e);
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
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
