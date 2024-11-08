package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.utils.domain.Province;
import ph.devcon.flag.core.port.persistence.ProvinceRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheProvinceRepository implements ProvinceRepository, PanacheRepository<Province> {

    @Override
    public List<Province> findAllProvinces() {
        return listAll(Sort.by("name"));
    }

    @Override
    public List<Province> findByRegion(String region) {
        return list("region", region);
    }

    @Override
    public Province findByName(String name) {
        return find("name", name).firstResult();
    }

    @Override
    public Province findById(int id) {
        return find("id", id).firstResult();
    }
}
