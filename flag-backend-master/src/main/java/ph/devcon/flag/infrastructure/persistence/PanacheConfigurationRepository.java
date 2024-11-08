package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.utils.domain.Configuration;
import ph.devcon.flag.core.port.persistence.ConfigurationRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheConfigurationRepository implements ConfigurationRepository, PanacheRepository<Configuration> {

    @Override
    public List<Configuration> findAllConfigurations() {
        return listAll(Sort.by("key"));
    }
    @Override
    public Configuration findByKey(String key) {
        return find("key", key).firstResult();
    }

    @Override
    public Configuration findById(int id) {
        return find("id", id).firstResult();
    }
}
