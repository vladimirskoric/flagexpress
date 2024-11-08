package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.donor.domain.SectorType;
import ph.devcon.flag.core.port.persistence.DonorSectorRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;;

@ApplicationScoped
public class PanacheDonorSectorRepository implements DonorSectorRepository, PanacheRepository<SectorType> {

    @Override
    public List<SectorType> findAllDonorSectorTypes() {
        return listAll(Sort.by("id"));
    }

    @Override
    public SectorType findByCode(String code) {
        return find("code", code).firstResult();
    }

    @Override
    public SectorType findById(int id) {
        return find("id", id).firstResult();
    }

}
