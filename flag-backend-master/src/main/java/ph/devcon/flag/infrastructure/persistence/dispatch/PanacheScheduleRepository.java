// package ph.devcon.flag.infrastructure.persistence.dispatch;

// import io.quarkus.hibernate.orm.panache.PanacheRepository;
// import ph.devcon.flag.core.component.dispatch.domain.Schedule;
// import ph.devcon.flag.core.port.persistence.dispatch.ScheduleRepository;

// import javax.enterprise.context.ApplicationScoped;
// import java.util.List;

// @ApplicationScoped
// public class PanacheScheduleRepository implements ScheduleRepository, PanacheRepository<Schedule> {

//     @Override
//     public List<Schedule> findAllSchedules() {
//         return listAll();
//     }

//     @Override
//     public Schedule findById(long id) {
//         return find("id",id).firstResult();
//     }

//     @Override
//     public Schedule findByDescription(String description) {
//         return find("description",description).firstResult();
//     }
// }
