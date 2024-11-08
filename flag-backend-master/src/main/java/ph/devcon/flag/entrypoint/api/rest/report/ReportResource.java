package ph.devcon.flag.entrypoint.api.rest.report;

import lombok.extern.slf4j.Slf4j;
import ph.devcon.flag.core.component.reports.ReportService;
import ph.devcon.flag.entrypoint.api.rest.error.JsonError;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.jboss.resteasy.spi.HttpResponseCodes.SC_INTERNAL_SERVER_ERROR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

@Slf4j
@Path("/reports")
@Produces(APPLICATION_JSON)
@ApplicationScoped
public class ReportResource {

    @Inject
    ReportService reportService;

    @GET
    @Path("/")
    public Response generateReports() throws FileNotFoundException {
        Response response = Response.ok().build();
        try {
            reportService.generateCashDonationReport();
            reportService.generateDomesticDonationReport();
            reportService.generateInternationalDonationReport();
            reportService.generateDispatchReport();
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
    @Path("/{year}")
    public Response generateYearlyReports(@PathParam("year") final Integer year) throws FileNotFoundException {
        Response response = Response.ok().build();
        try {
            String path = reportService.generateReportByYear(year);
            File fileDownload = new File(path);
            ResponseBuilder rsp = Response.ok((Object) fileDownload);
            rsp.header("Content-Disposition", "attachment;filename=" + fileDownload);
            return rsp.build();
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