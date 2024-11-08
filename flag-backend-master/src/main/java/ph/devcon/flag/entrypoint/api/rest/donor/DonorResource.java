package ph.devcon.flag.entrypoint.api.rest.donor;

import lombok.extern.slf4j.Slf4j;
import ph.devcon.flag.core.component.donor.application.DonorDTO;
import ph.devcon.flag.core.component.donor.application.DonorService;
import ph.devcon.flag.core.component.exception.InvalidRequestException;
import ph.devcon.flag.entrypoint.api.rest.error.JsonError;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("/donors")
@Produces(APPLICATION_JSON)
@ApplicationScoped
public class DonorResource {

    @Inject
    DonorService donorService;

    @POST
    @Path("/")
    public Response createDonor(DonorDTO dto) throws FileNotFoundException {
        Response response = Response.status(SC_CREATED).build();
        try {
            donorService.createUpdateDonor(dto);
        } catch (InvalidRequestException e) {
            log.error("error occurred querying by id", e);
            JsonError error = JsonError.builder().status("400").title("Invalid Request").detail("TODO: FIX THIS")
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
    public Response getDonors() throws FileNotFoundException {
        Response response;
        try {
            List<DonorDTO> donors = donorService.getDonors();
            response = Response.ok(donors).build();
        } catch (Exception e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
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

    @GET
    @Path("/{id}")
    public Response getDonor(@PathParam("id") final int id) {
        Response response;
        try {
            DonorDTO donor = donorService.getDonorById(id);
            response = Response.ok(donor).build();
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

    @GET
    @Path("/mobile/{number}")
    public Response getDonorByMobile(@PathParam("number") String number) throws FileNotFoundException {
        Response response;
        try {
            DonorDTO donor = donorService.getDonorByMobile(number);
            response = Response.ok(donor).build();
        } catch (Exception e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
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

    @GET
    @Path("/sector/{id}")
    public Response getDonorBySector(@PathParam("id") int sectorId) throws FileNotFoundException {
        Response response;
        try {
            List<DonorDTO> donors = donorService.getDonorsBySector(sectorId);
            response = Response.ok(donors).build();
        } catch (Exception e) {
            PrintStream s = new PrintStream("debug.txt");
            e.printStackTrace(s);
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
