// package ph.devcon.flag.infrastructure.persistence.dispatch;

// import io.quarkus.hibernate.orm.panache.PanacheRepository;
// import io.quarkus.panache.common.Parameters;
// import ph.devcon.flag.core.component.dispatch.domain.DeliverySchedule;
// import ph.devcon.flag.core.port.persistence.dispatch.DeliveryScheduleRepository;

// import javax.enterprise.context.ApplicationScoped;
// import java.util.List;

// ;

// @ApplicationScoped
// public class PanacheDeliveryScheduleRepository implements DeliveryScheduleRepository, PanacheRepository<DeliverySchedule> {


//     @Override
//     public DeliverySchedule findActiveDeliveryScheduleByDispatchId(long id) {
//         return find("dispatch_id = :id and is_active = :isActive",
//                 Parameters.with("id", id)
//                         .and("isActive",true)).firstResult();

//     }

//     @Override
//     public List<DeliverySchedule> findAllDeliveryScheduleByDispatchId(long id) {
//         return find("dispatch_id",id).list();
//     }

//     @Override
//     public DeliverySchedule createDeliverySchedule(DeliverySchedule schedule) {
//         persistAndFlush(schedule);
//         return schedule;
//     }
// }
