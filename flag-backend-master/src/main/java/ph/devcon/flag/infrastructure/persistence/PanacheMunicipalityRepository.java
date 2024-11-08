package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.utils.domain.Municipality;
import ph.devcon.flag.core.port.persistence.MunicipalityRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheMunicipalityRepository implements MunicipalityRepository, PanacheRepository<Municipality> {

    @Override
    public List<Municipality> findAllMunicipalities() {
        return listAll(Sort.by("name"));
    }

    @Override
    public List<Municipality> findByProvince(String province) {
        return list("province", province);
    }

    @Override
    public Municipality findByName(String name) {
        return find("name", name).firstResult();
    }

    @Override
    public Municipality findById(int id) {
        return find("id", id).firstResult();
    }
}
