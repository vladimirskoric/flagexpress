package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.utils.domain.DonationUnit;

public interface UnitRepository {
    List<DonationUnit> findAllUnits();
    DonationUnit findByCode(String code);
    DonationUnit findByName(String name);
    DonationUnit findById(int id);
}


