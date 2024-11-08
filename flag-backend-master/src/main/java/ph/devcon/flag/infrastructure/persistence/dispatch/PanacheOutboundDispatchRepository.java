// package ph.devcon.flag.infrastructure.persistence.dispatch;

// import io.quarkus.hibernate.orm.panache.PanacheRepository;
// import io.quarkus.panache.common.Sort;
// import ph.devcon.flag.core.component.dispatch.domain.OutboundDispatch;
// import ph.devcon.flag.core.port.persistence.dispatch.OutboundDispatchRepository;

// import java.util.List;

// import javax.enterprise.context.ApplicationScoped;;

// @ApplicationScoped
// public class PanacheOutboundDispatchRepository implements OutboundDispatchRepository, PanacheRepository<OutboundDispatch> {
    
//     @Override
//     public OutboundDispatch findById(final long id) {
//         return find("id", id).firstResult();
//     }

//     @Override
//     public List<OutboundDispatch> findAllDispatches(String status, String startDate, String endDate) {
//         return listAll(Sort.ascending("created_time"));
//     }
    
//     @Override
//     public OutboundDispatch findByFleetId(long id) {
//         return find("fleet_id", id).firstResult();
//     }

//     @Override
//     public OutboundDispatch createOutboundDispatch(OutboundDispatch dispatch) {
//         persistAndFlush(dispatch);
//         return dispatch;
//     }
// }
