// package ph.devcon.flag.infrastructure.persistence.dispatch;

// import io.quarkus.hibernate.orm.panache.PanacheRepository;
// import io.quarkus.panache.common.Parameters;
// import ph.devcon.flag.core.component.fleet.domain.FleetAssignment;
// import ph.devcon.flag.core.port.persistence.dispatch.FleetAssignmentRepository;

// import javax.enterprise.context.ApplicationScoped;
// import java.util.List;

// @ApplicationScoped
// public class PanacheFleetAssignmentRepository implements FleetAssignmentRepository, PanacheRepository<FleetAssignment> {

//     @Override
//     public List<FleetAssignment> findByFleetName(String fleetName) {
//         return list("fleet.name = :fleetName", Parameters.with("fleetName", fleetName));
//     }

//     @Override
//     public List<FleetAssignment> findByFleetLeader(String leaderName) {
//         return find("fleet_leader", leaderName).list();
//     }

//     @Override
//     public FleetAssignment createFleetAssignment(FleetAssignment fleetAssignment) {
//         persistAndFlush(fleetAssignment);
//         return fleetAssignment;
//     }
// }