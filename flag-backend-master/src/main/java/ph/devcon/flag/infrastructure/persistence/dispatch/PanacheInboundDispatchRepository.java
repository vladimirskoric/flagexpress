// package ph.devcon.flag.infrastructure.persistence.dispatch;

// import io.quarkus.hibernate.orm.panache.PanacheRepository;
// import io.quarkus.panache.common.Sort;
// import ph.devcon.flag.core.component.dispatch.domain.InboundDispatch;
// import ph.devcon.flag.core.port.persistence.dispatch.InboundDispatchRepository;

// import java.util.List;

// import javax.enterprise.context.ApplicationScoped;
// import javax.inject.Inject;
// import javax.persistence.EntityManager;;

// @ApplicationScoped
// public class PanacheInboundDispatchRepository implements InboundDispatchRepository, PanacheRepository<InboundDispatch> {

//     @Inject
//     protected EntityManager em;

//     @Override
//     public InboundDispatch findById(long id) {
//         return find("id", id).firstResult();
//     }

//     @Override
//     public List<InboundDispatch> findAllDispatches(String status, String startDate, String endDate) {
//         return listAll(Sort.ascending("created_time"));
//     }

//     /**
//      * Get Active Inbound Dispatch by Fleet Id
//      * 
//      * @param id
//      * @return
//      */
//     @Override
//     public InboundDispatch findByFleetId(long id) {
//         return find("fleet_id", id).firstResult();
//     }

//     @Override
//     public InboundDispatch createUpdateInboundDispatch(InboundDispatch dispatch) {

//         if (dispatch.getId() != 0)
//             em.merge(dispatch);
//         else
//             persist(dispatch);

//         flush();
//         return dispatch;
//     }
// }
