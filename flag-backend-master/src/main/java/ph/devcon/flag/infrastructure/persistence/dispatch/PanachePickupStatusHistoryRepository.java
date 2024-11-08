// package ph.devcon.flag.infrastructure.persistence.dispatch;

// import io.quarkus.hibernate.orm.panache.PanacheRepository;
// import io.quarkus.panache.common.Parameters;
// import io.quarkus.panache.common.Sort;
// import ph.devcon.flag.core.component.dispatch.domain.PickupStatusHistory;
// import ph.devcon.flag.core.port.persistence.dispatch.PickupStatusHistoryRepository;

// import java.util.List;

// import javax.enterprise.context.ApplicationScoped;;

// @ApplicationScoped
// public class PanachePickupStatusHistoryRepository implements PickupStatusHistoryRepository, PanacheRepository<PickupStatusHistory> {


//     @Override
//     public List<PickupStatusHistory> findAllByDispatchId(long id) {
//         return list("inbound_id", Sort.ascending("created_time"));
//     }

//     @Override
//     public PickupStatusHistory findActiveStatusByDispatchId(long id) {
//         return find("inbound_id = :id and is_active = :isActive",
//                 Parameters.with("id", id)
//                         .and("isActive",true))
//                 .firstResult();
//     }


//     @Override
//     public PickupStatusHistory createPickupStatus(PickupStatusHistory pickupStatusHistory) {
//         persistAndFlush(pickupStatusHistory);
//         return pickupStatusHistory;
//     }
// }
