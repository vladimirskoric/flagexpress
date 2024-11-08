package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.beneficiary.domain.BeneficiarySector;
import ph.devcon.flag.core.port.persistence.BeneficiarySectorRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;;

@ApplicationScoped
public class PanacheBeneficiarySectorRepository implements BeneficiarySectorRepository, PanacheRepository<BeneficiarySector> {

    @Override
    public List<BeneficiarySector> findAllSectors() {
        return listAll(Sort.by("id"));
    }

    @Override
    public BeneficiarySector findByCode(String code) {
        return find("code", code).firstResult();
    }

    @Override
    public BeneficiarySector findById(int id) {
        return find("id", id).firstResult();
    }

}
