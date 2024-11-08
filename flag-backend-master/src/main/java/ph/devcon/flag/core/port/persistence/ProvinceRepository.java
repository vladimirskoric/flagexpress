package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.utils.domain.Province;

public interface ProvinceRepository {
    List<Province> findAllProvinces();
    List<Province> findByRegion(String region);
    Province findByName(String name);
    Province findById(int id);
}

