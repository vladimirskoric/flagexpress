package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.utils.domain.Region;
import ph.devcon.flag.core.port.persistence.RegionRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheRegionRepository implements RegionRepository, PanacheRepository<Region> {

    @Override
    public List<Region> findAllRegions() {
        return listAll(Sort.by("id"));
    }

    @Override
    public Region findByCode(String code) {
        return find("code", code).firstResult();
    }

    @Override
    public Region findByName(String name) {
        return find("name", name).firstResult();
    }

    @Override
    public Region findById(int id) {
        return find("id", id).firstResult();
    }
}
