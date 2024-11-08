package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.beneficiary.domain.BeneficiarySector;

public interface BeneficiarySectorRepository {
    List<BeneficiarySector> findAllSectors();
    BeneficiarySector findByCode(String code);
    BeneficiarySector findById(int id);
}

