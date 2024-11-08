
package ph.devcon.flag.entrypoint.api.rest.utils;

import lombok.extern.slf4j.Slf4j;
import ph.devcon.flag.core.component.beneficiary.application.BeneficiarySectorDTO;
import ph.devcon.flag.core.component.donationrequest.application.RequestTypeDTO;
import ph.devcon.flag.core.component.utils.application.*;
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

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

@Slf4j
@Path("/static")
@Produces(APPLICATION_JSON)
@ApplicationScoped
public class StaticValueResource{

    @Inject
    StaticValueService utilityService;

    @GET
    @Path("/currencies")
    public Response getCurrencies() throws FileNotFoundException {
        Response response;
        try {
            List<CurrencyDTO> currencies = utilityService.getCurrencies();
            response = Response.ok(currencies).build();
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
    @Path("/donationtypes")
    public Response getDonationTypes() throws FileNotFoundException {
        Response response;
        try {
            List<DonationTypeDTO> donationTypes = utilityService.getDonationTypes();
            response = Response.ok(donationTypes).build();
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
    @Path("/donorsectors")
    public Response getDonorSectors() throws FileNotFoundException {
        Response response;
        try {
            List<SectorTypeDTO> donorSectors = utilityService.getDonorSectors();
            response = Response.ok(donorSectors).build();
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
    @Path("/units")
    public Response getUnits() throws FileNotFoundException {
        Response response;
        try {
            List<DonationUnitDTO> units = utilityService.getUnits();
            response = Response.ok(units).build();
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
    @Path("/servicetypes")
    public Response getServiceTypes() throws FileNotFoundException {
        Response response;
        try {
            List<ServiceTypeDTO> serviceTypes = utilityService.getServiceTypes();
            response = Response.ok(serviceTypes).build();
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
    @Path("/beneficiarysectors")
    public Response getBeneficiarySectors() throws FileNotFoundException {
        Response response;
        try {
            List<BeneficiarySectorDTO> beneficiarySectors = utilityService.getBeneficiarySectors();
            response = Response.ok(beneficiarySectors).build();
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
    @Path("/requesttypes")
    public Response getDonationRequestTypes() throws FileNotFoundException {
        Response response;
        try {
            List<RequestTypeDTO> requestTypes = utilityService.getDonationRequestTypes();
            response = Response.ok(requestTypes).build();
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
    @Path("/items")
    public Response getDonationItems() throws FileNotFoundException {
        Response response;
        try {
            List<ItemDTO> items = utilityService.getAllDonationItems();
            response = Response.ok(items).build();
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
    @Path("/regions")
    public Response getRegions() throws FileNotFoundException {
        Response response;
        try {
            List<RegionDTO> regions = utilityService.getAllRegions();
            response = Response.ok(regions).build();
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
    @Path("/provinces")
    public Response getProvinces() throws FileNotFoundException {
        Response response;
        try {
            List<ProvinceDTO> provinces = utilityService.getAllProvinces();
            response = Response.ok(provinces).build();
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
    @Path("{region}/provinces")
    public Response getProvincesByRegion(@PathParam("region") final String region) throws FileNotFoundException {
        Response response;
        try {
            List<ProvinceDTO> provinces = utilityService.getProvincesByRegion(region);
            response = Response.ok(provinces).build();
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
    @Path("/municipalities")
    public Response getMunicipalities(@PathParam("province") final String province) throws FileNotFoundException {
        Response response;
        try {
            List<MunicipalityDTO> provinces = utilityService.getAllMunicipalities();
            response = Response.ok(provinces).build();
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
    @Path("{province}/municipalities")
    public Response getMunicipalitiesByProvince(@PathParam("province") final String province) throws FileNotFoundException {
        Response response;
        try {
            List<MunicipalityDTO> provinces = utilityService.getMunicipalitiesByProvince(province);
            response = Response.ok(provinces).build();
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
    @Path("/countries")
    public Response getCountries() throws FileNotFoundException {
        Response response;
        try {
            List<CountryDTO> countries = utilityService.getAllCountries();
            response = Response.ok(countries).build();
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
