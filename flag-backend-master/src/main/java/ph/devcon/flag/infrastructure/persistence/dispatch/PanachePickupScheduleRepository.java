// package ph.devcon.flag.infrastructure.persistence.dispatch;

// import io.quarkus.hibernate.orm.panache.PanacheRepository;
// import io.quarkus.panache.common.Parameters;
// import ph.devcon.flag.core.component.dispatch.domain.PickupSchedule;
// import ph.devcon.flag.core.port.persistence.dispatch.PickupScheduleRepository;

// import javax.enterprise.context.ApplicationScoped;
// import javax.inject.Inject;
// import javax.persistence.EntityManager;
// import java.util.List;

// @ApplicationScoped
// public class PanachePickupScheduleRepository implements PickupScheduleRepository, PanacheRepository<PickupSchedule> {

//     @Inject
//     protected EntityManager em;

//     @Override
//     public PickupSchedule findActivePickupScheduleByDispatchId(long id) {
//         return find("pickup_id = :id and is_active = :isActive",
//                 Parameters.with("id", id)
//                         .and("isActive",true))
//                 .firstResult();
//     }

//     @Override
//     public List<PickupSchedule> findAllPickupScheduleByDispatchId(long id) {
//         return list("pickup_id", id);
//     }

//     @Override
//     public PickupSchedule createUpdatePickupSchedule(PickupSchedule schedule) {
//         if(schedule.getId() != 0)
//             em.merge(schedule);
//         else
//             persist(schedule);

//         flush();
//         return schedule;
//     }
// }
