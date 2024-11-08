// package ph.devcon.flag.core.component.utils.helper.Mappers;

// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;

// import ph.devcon.flag.core.component.fleet.application.FleetAssignmentDTO;
// import ph.devcon.flag.core.component.fleet.application.FleetDTO;
// import ph.devcon.flag.core.component.fleet.domain.Fleet;
// import ph.devcon.flag.core.component.fleet.domain.FleetAssignment;

// public final class FleetMapper {

//     public static List<FleetAssignmentDTO> toFleetAssignmentDTOs(List<FleetAssignment> fltAssignments) {
//         List<FleetAssignmentDTO> dtos = new ArrayList<FleetAssignmentDTO>();
//         for(FleetAssignment assignment: fltAssignments){
//             FleetAssignmentDTO dto = toFleetAssignmentDTO(assignment);
//             dtos.add(dto);
//         }
//         return dtos;
//     }

//     public static List<FleetAssignment> toFleetAssignments(List<FleetAssignmentDTO> dtos, Fleet fleet) {
//         List<FleetAssignment> fleetAssignments = new ArrayList<FleetAssignment>();
//         for(FleetAssignmentDTO item: dtos){
//             FleetAssignment assignment = toFleetAssignment(item, fleet);
//             fleetAssignments.add(assignment);
//         }

//         return fleetAssignments;
//     }

//     public static FleetAssignment toFleetAssignment(FleetAssignmentDTO dto, Fleet fleet) {
//         FleetAssignment fleetDomain = new FleetAssignment();
//         fleetDomain.setFleet(fleet);
//         fleetDomain.setFleetLeader(dto.getLeader());
//         fleetDomain.setFleetContact(dto.getFleetContact());
//         fleetDomain.setCreatedTime(new Date());

//         return fleetDomain;
//     }

//     public static FleetAssignmentDTO toFleetAssignmentDTO(FleetAssignment fleetAssignment) {
//         FleetAssignmentDTO dto = new FleetAssignmentDTO();

//         if(fleetAssignment == null)
//             return dto;
            
//         dto.setFleet(toFleetDTO(fleetAssignment.getFleet()));
//         dto.setLeader(fleetAssignment.getFleetLeader());
//         dto.setFleetContact(dto.getFleetContact());
//         dto.setCreatedTime(fleetAssignment.getCreatedTime());

//         return dto;
//     }

//     public static Fleet toFleet(FleetDTO dto) {
//         Fleet fleetDomain = new Fleet();
//         fleetDomain.setName(dto.getName());
//         fleetDomain.setDescription(dto.getDescription());
//         return fleetDomain;
//     }

//     public static FleetDTO toFleetDTO(Fleet fleet) {
//         FleetDTO dto = new FleetDTO();
//         dto.setName(fleet.getName());
//         dto.setDescription(fleet.getDescription());
//         return dto;
//     }

// }