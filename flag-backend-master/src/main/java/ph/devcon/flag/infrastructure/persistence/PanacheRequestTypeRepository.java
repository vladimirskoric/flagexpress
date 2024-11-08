package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.donationrequest.domain.RequestType;
import ph.devcon.flag.core.port.persistence.RequestTypeRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheRequestTypeRepository implements RequestTypeRepository, PanacheRepository<RequestType> {
    @Override
    public RequestType findByName(String name) {
        return find("name", name).firstResult();
    }

    @Override
    public List<RequestType> findAllRequestTypes() {
        return listAll(Sort.by("name"));
    }
}
