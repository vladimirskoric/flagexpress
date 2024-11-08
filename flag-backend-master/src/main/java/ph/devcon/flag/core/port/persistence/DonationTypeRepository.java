package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.utils.domain.DonationType;

public interface DonationTypeRepository {
    List<DonationType> findAllDonationTypes();
    DonationType findByCode(String code);
    DonationType findById(int id);
}


