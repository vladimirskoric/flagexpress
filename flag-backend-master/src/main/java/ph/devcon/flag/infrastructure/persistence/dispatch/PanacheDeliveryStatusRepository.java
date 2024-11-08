// package ph.devcon.flag.infrastructure.persistence.dispatch;

// import io.quarkus.hibernate.orm.panache.PanacheRepository;
// import ph.devcon.flag.core.component.dispatch.domain.DeliveryStatus;
// import ph.devcon.flag.core.port.persistence.dispatch.DeliveryStatusRepository;

// import java.util.List;

// import javax.enterprise.context.ApplicationScoped;;

// @ApplicationScoped
// public class PanacheDeliveryStatusRepository implements DeliveryStatusRepository, PanacheRepository<DeliveryStatus> {

//     @Override
//     public List<DeliveryStatus> findAllDeliveryStatus() {
//         return  listAll();
//     }

//     @Override
//     public DeliveryStatus findByName(String name) {
//         return find("name", name).firstResult();
//     }

//     @Override
//     public DeliveryStatus findById(long id) {
//         return find("id", id).firstResult();
//     }

// }
