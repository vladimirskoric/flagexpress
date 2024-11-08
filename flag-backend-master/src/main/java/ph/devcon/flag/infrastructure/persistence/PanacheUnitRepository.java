package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.utils.domain.DonationUnit;
import ph.devcon.flag.core.port.persistence.UnitRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheUnitRepository implements UnitRepository, PanacheRepository<DonationUnit> {

    @Override
    public List<DonationUnit> findAllUnits() {
        return listAll(Sort.by("code"));
    }

    @Override
    public DonationUnit findByCode(String code) {
        return find("code", code).firstResult();
    }

    @Override
    public DonationUnit findByName(String name) {
        return find("name", name).firstResult();
    }

    @Override
    public DonationUnit findById(int id) {
        return find("id", id).firstResult();
    }
}
