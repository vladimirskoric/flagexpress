package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.utils.domain.DonationType;
import ph.devcon.flag.core.port.persistence.DonationTypeRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheDonationTypeRepository implements DonationTypeRepository, PanacheRepository<DonationType> {

    @Override
    public List<DonationType> findAllDonationTypes() {
        return listAll(Sort.by("id"));
    }

    @Override
    public DonationType findByCode(String code) {
        return find("code", code).firstResult();
    }

    @Override
    public DonationType findById(int id) {
        return find("id", id).firstResult();
    }
}
