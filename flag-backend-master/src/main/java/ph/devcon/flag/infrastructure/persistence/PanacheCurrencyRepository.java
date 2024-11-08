package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.utils.domain.Currency;
import ph.devcon.flag.core.port.persistence.CurrencyRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheCurrencyRepository implements CurrencyRepository, PanacheRepository<Currency> {

    @Override
    public List<Currency> findAllCurrencies() {
        return listAll(Sort.by("code"));
    }

    @Override
    public Currency findByCode(String code) {
        return find("code", code).firstResult();
    }

    @Override
    public Currency findByName(String name) {
        return find("name", name).firstResult();
    }

    @Override
    public Currency findById(int id) {
        return find("id", id).firstResult();
    }
}
