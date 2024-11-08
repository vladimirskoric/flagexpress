package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.utils.domain.Country;
import ph.devcon.flag.core.port.persistence.CountryRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheCountryRepository implements CountryRepository, PanacheRepository<Country> {

    @Override
    public List<Country> findAllCountries() {
        return listAll(Sort.by("name"));
    }

    @Override
    public Country findByName(String name) {
        return find("name", name).firstResult();
    }

    @Override
    public Country findById(int id) {
        return find("id", id).firstResult();
    }
}
