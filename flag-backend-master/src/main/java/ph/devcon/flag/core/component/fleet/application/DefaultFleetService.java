// package ph.devcon.flag.core.component.fleet.application;

// import ph.devcon.flag.core.component.fleet.domain.Fleet;
// import ph.devcon.flag.core.component.fleet.domain.FleetAssignment;
// import ph.devcon.flag.core.component.utils.helper.Mappers.*;
// import ph.devcon.flag.core.port.persistence.dispatch.FleetAssignmentRepository;
// import ph.devcon.flag.core.port.persistence.dispatch.FleetRepository;

// import javax.enterprise.context.ApplicationScoped;
// import javax.inject.Inject;
// import javax.transaction.Transactional;

// import java.util.ArrayList;
// import java.util.List;

// @ApplicationScoped
// public class DefaultFleetService implements FleetService {
//     @Inject
//     FleetRepository fleetRepository;
//     @Inject
//     FleetAssignmentRepository fleetAssignmentRepository;

//     @Override
//     public List<FleetDTO> getAllFleets() {
//         List<Fleet> fleets = fleetRepository.findAllFleets();
//         List<FleetDTO> dtos = new ArrayList<FleetDTO>();

//         for(Fleet fleet: fleets){
//             FleetDTO dto = FleetMapper.toFleetDTO(fleet);
//             dtos.add(dto);
//         }

//         return dtos;
//     }

//     @Override
//     public FleetDTO getByName(String fleetName) {
//         return FleetMapper.toFleetDTO(fleetRepository.findByName(fleetName));
//     }

//     @Transactional
//     @Override
//     public FleetDTO createUpdateFleet(FleetDTO fleet) {
//         Fleet flt = FleetMapper.toFleet(fleet);
//         fleetRepository.createUpdateFleet(flt);
//         return fleet;
//     }

//     @Transactional
//     @Override
//     public FleetAssignmentDTO createFleetAssignment(FleetAssignmentDTO fleetAssignment) {
//         Fleet fleet = fleetRepository.findByName(fleetAssignment.getFleet().getName());
//         FleetAssignment fltAssignment = FleetMapper.toFleetAssignment(fleetAssignment, fleet);
//         fleetAssignmentRepository.createFleetAssignment(fltAssignment);
//         return fleetAssignment;
//     }

//     @Override
//     public List<FleetAssignmentDTO> getAssignmentByName(String fleetName) {
//         List<FleetAssignment> fltAssignments = fleetAssignmentRepository.findByFleetName(fleetName);
//         return FleetMapper.toFleetAssignmentDTOs(fltAssignments);
//     }


//     @Override
//     public List<FleetAssignmentDTO> getAssignmentByLeader(String leaderName) {
//         List<FleetAssignment> fltAssignments = fleetAssignmentRepository.findByFleetLeader(leaderName);
//         return FleetMapper.toFleetAssignmentDTOs(fltAssignments);
//     }

// }
