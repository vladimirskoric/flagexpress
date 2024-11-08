// package ph.devcon.flag.infrastructure.persistence.dispatch;

// import io.quarkus.hibernate.orm.panache.PanacheRepository;
// import io.quarkus.panache.common.Parameters;
// import io.quarkus.panache.common.Sort;
// import ph.devcon.flag.core.component.dispatch.domain.DeliveryStatusHistory;
// import ph.devcon.flag.core.port.persistence.dispatch.DeliveryStatusHistoryRepository;

// import java.util.List;

// import javax.enterprise.context.ApplicationScoped;;

// @ApplicationScoped
// public class PanacheDeliveryStatusHistoryRepository implements DeliveryStatusHistoryRepository, PanacheRepository<DeliveryStatusHistory> {

//     @Override
//     public List<DeliveryStatusHistory> findAllByDispatchId(long id) {
//         return list("outbound_id", Sort.ascending("created_time"));
//     }

//     @Override
//     public DeliveryStatusHistory findActiveStatusByDispatchId(long id) {
//         return find("outbound_id = :id and is_active = :isActive",
//                 Parameters.with("id", id)
//                         .and("isActive",true))
//                 .firstResult();
//     }

//     @Override
//     public DeliveryStatusHistory createDeliveryStatus(DeliveryStatusHistory deliveryStatus) {
//         persistAndFlush(deliveryStatus);
//         return deliveryStatus;
//     }
// }
