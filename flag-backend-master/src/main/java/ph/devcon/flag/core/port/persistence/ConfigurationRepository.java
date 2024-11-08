package ph.devcon.flag.core.port.persistence;

import java.util.List;
import ph.devcon.flag.core.component.utils.domain.Configuration;

public interface ConfigurationRepository {
    List<Configuration> findAllConfigurations();
    Configuration findByKey(String name);
    Configuration findById(int id);
}

