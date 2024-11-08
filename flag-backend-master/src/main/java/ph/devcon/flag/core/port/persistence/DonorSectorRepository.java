package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.donor.domain.SectorType;

public interface DonorSectorRepository {
    List<SectorType> findAllDonorSectorTypes();
    SectorType findByCode(String code);
    SectorType findById(int id);
}

