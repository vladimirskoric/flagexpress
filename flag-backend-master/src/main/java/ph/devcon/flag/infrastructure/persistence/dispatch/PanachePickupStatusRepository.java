// package ph.devcon.flag.infrastructure.persistence.dispatch;

// import io.quarkus.hibernate.orm.panache.PanacheRepository;
// import ph.devcon.flag.core.component.dispatch.domain.PickupStatus;
// import ph.devcon.flag.core.port.persistence.dispatch.PickupStatusRepository;

// import java.util.List;

// import javax.enterprise.context.ApplicationScoped;;

// @ApplicationScoped
// public class PanachePickupStatusRepository implements PickupStatusRepository, PanacheRepository<PickupStatus> {

//     @Override
//     public List<PickupStatus> findAllPickupStatus() {
//         return listAll();
//     }

//     @Override
//     public PickupStatus findByName(String name) {
//         return find("name", name).firstResult();
//     }

//     @Override
//     public PickupStatus findById(long id) {
//         return find("id",id).firstResult();
//     }

// }
