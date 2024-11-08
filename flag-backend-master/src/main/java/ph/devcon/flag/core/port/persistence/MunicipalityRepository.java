package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.utils.domain.Municipality;

public interface MunicipalityRepository {
    List<Municipality> findAllMunicipalities();
    List<Municipality> findByProvince(String province);
    Municipality findByName(String name);
    Municipality findById(int id);
}

