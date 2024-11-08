package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.donations.domain.ServiceType;
import ph.devcon.flag.core.port.persistence.ServiceTypeRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheServiceTypeRepository implements ServiceTypeRepository, PanacheRepository<ServiceType> {

    @Override
    public List<ServiceType> findAllServiceTypes() {
        return listAll(Sort.by("id"));
    }

    @Override
    public ServiceType findByCode(String code) {
        return find("code", code).firstResult();
    }

    @Override
    public ServiceType findById(int id) {
        return find("id", id).firstResult();
    }
}
