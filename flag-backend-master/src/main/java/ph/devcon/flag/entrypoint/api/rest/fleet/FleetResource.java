// package ph.devcon.flag.entrypoint.api.rest.fleet;

// import lombok.extern.slf4j.Slf4j;
// import ph.devcon.flag.core.component.exception.InvalidRequestException;
// import ph.devcon.flag.core.component.fleet.application.FleetAssignmentDTO;
// import ph.devcon.flag.core.component.fleet.application.FleetDTO;
// import ph.devcon.flag.core.component.fleet.application.FleetService;
// import ph.devcon.flag.entrypoint.api.rest.error.JsonError;

// import javax.enterprise.context.ApplicationScoped;
// import javax.inject.Inject;
// import javax.ws.rs.*;
// import javax.ws.rs.core.Response;
// import java.io.FileNotFoundException;
// import java.io.PrintStream;
// import java.util.ArrayList;
// import java.util.List;

// import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
// import static org.jboss.resteasy.spi.HttpResponseCodes.SC_BAD_REQUEST;
// import static org.jboss.resteasy.spi.HttpResponseCodes.SC_INTERNAL_SERVER_ERROR;


// @Slf4j
// @Path("/fleets")
// @Produces(APPLICATION_JSON)
// @ApplicationScoped
// public class FleetResource {

//     @Inject
//     public FleetService fleetService;

//     @POST
//     @PUT
//     @Path("/")
//     public Response createFleet(FleetDTO dto) throws FileNotFoundException {
//         Response response = Response.ok().build();
//         try {
//             FleetDTO fleet = fleetService.createUpdateFleet(dto);
//             response = Response.ok(fleet).build();
//         } catch (InvalidRequestException e) {
//             log.error("error occurred querying by id", e);
//             PrintStream s = new PrintStream("debug.txt");
//             e.printStackTrace(s);
//             JsonError error = JsonError.builder().status("400").title("Invalid Request").detail(e.getMessage())
//                     .build();
//             response = Response.status(SC_BAD_REQUEST).entity(error).build();
//         } catch (Exception e) {
//             PrintStream s = new PrintStream("debug.txt");
//             e.printStackTrace(s);
//             log.error("error occurred querying by id", e);
//             JsonError error = JsonError.builder().status("500").title("An Unknown Error has occurred")
//                     .detail(e.getMessage()).build();

//             response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
//         }

//         return response;
//     }

//     @GET
//     @Path("/")
//     public Response getFleets(@QueryParam("name") String name) throws FileNotFoundException {
//         Response response;
//         try {
//             if (name != null && !name.isEmpty()) {
//                 FleetDTO fleet = fleetService.getByName(name);
//                 response = Response.ok(fleet).build();
//             } else {
//                 List<FleetDTO> fleets = fleetService.getAllFleets();
//                 response = Response.ok(fleets).build();
//             }
//         } catch (Exception e) {
//             log.error("error occurred querying by id", e);
//             PrintStream s = new PrintStream("debug.txt");
//             e.printStackTrace(s);
//             JsonError error = JsonError.builder()
//                     .status("500")
//                     .title("An Unknown Error has occurred")
//                     .detail("TODO: FIX THIS")
//                     .build();

//             response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
//         }

//         return response;
//     }

//     @GET
//     @Path("/assignment")
//     public Response getFleetAssignments(@QueryParam("fleetName") String fleetName, @QueryParam("fleetLeader") String fleetLeader) throws FileNotFoundException {
//         Response response = Response.ok().build();
//         try {
//             List<FleetAssignmentDTO> fleetAssignments = new ArrayList<FleetAssignmentDTO>();
//             if (fleetName != null && !fleetName.isEmpty()){
//                 fleetAssignments = fleetService.getAssignmentByName(fleetName);
//             } else if (fleetLeader != null && !fleetLeader.isEmpty()){
//                 fleetAssignments = fleetService.getAssignmentByLeader(fleetLeader);
//             }
      
//             response = Response.ok(fleetAssignments).build();
//         } catch (InvalidRequestException e) {
//             log.error("error occurred querying by id", e);
//             PrintStream s = new PrintStream("debug.txt");
//             e.printStackTrace(s);
//             JsonError error = JsonError.builder().status("400").title("Invalid Request").detail(e.getMessage())
//                     .build();
//             response = Response.status(SC_BAD_REQUEST).entity(error).build();
//         } catch (Exception e) {
//             PrintStream s = new PrintStream("debug.txt");
//             e.printStackTrace(s);
//             log.error("error occurred querying by id", e);
//             JsonError error = JsonError.builder().status("500").title("An Unknown Error has occurred")
//                     .detail(e.getMessage()).build();

//             response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
//         }

//         return response;
//     }

//     @POST
//     @Path("/assignment")
//     public Response createFleetAssignment(FleetAssignmentDTO dto) throws FileNotFoundException {
//         Response response = Response.ok().build();
//         try {
//                 FleetAssignmentDTO fleetAssignment = fleetService.createFleetAssignment(dto);
//                 response = Response.ok(fleetAssignment).build();
//         } catch (InvalidRequestException e) {
//             log.error("error occurred querying by id", e);
//             PrintStream s = new PrintStream("debug.txt");
//             e.printStackTrace(s);
//             JsonError error = JsonError.builder().status("400").title("Invalid Request").detail(e.getMessage())
//                     .build();
//             response = Response.status(SC_BAD_REQUEST).entity(error).build();
//         } catch (Exception e) {
//             PrintStream s = new PrintStream("debug.txt");
//             e.printStackTrace(s);
//             log.error("error occurred querying by id", e);
//             JsonError error = JsonError.builder().status("500").title("An Unknown Error has occurred")
//                     .detail(e.getMessage()).build();

//             response = Response.status(SC_INTERNAL_SERVER_ERROR).entity(error).build();
//         }

//         return response;
//     }

// }
