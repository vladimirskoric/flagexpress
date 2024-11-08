// package ph.devcon.flag.infrastructure.persistence.dispatch;

// import io.quarkus.hibernate.orm.panache.PanacheRepository;
// import io.quarkus.panache.common.Sort;
// import ph.devcon.flag.core.component.fleet.domain.Fleet;
// import ph.devcon.flag.core.port.persistence.dispatch.FleetRepository;

// import javax.enterprise.context.ApplicationScoped;
// import javax.inject.Inject;
// import javax.persistence.EntityManager;
// import java.util.List;

// @ApplicationScoped
// public class PanacheFleetRepository implements FleetRepository, PanacheRepository<Fleet> {
//     @Inject
//     EntityManager em;

//     @Override
//     public List<Fleet> findAllFleets() {
//         return listAll(Sort.ascending("name"));
//     }

//     @Override
//     public Fleet findByName(String fleetName) {
//         return find("name",fleetName).firstResult();
//     }

//     @Override
//     public Fleet createUpdateFleet(Fleet fleet) {
//         Fleet fleetDomain = findByName(fleet.getName());
//         if (fleetDomain == null) {
//             fleetDomain = fleet;
//         }  else {
//             fleetDomain.setName(fleet.getName());
//             fleetDomain.setDescription(fleet.getDescription());
//             em.merge(fleetDomain);
//         }
//         persistAndFlush(fleetDomain);
//         return fleetDomain;
//     }
// }
