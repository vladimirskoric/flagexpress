package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.utils.domain.Region;

public interface RegionRepository {
    List<Region> findAllRegions();
    Region findByCode(String code);
    Region findByName(String name);
    Region findById(int id);
}

