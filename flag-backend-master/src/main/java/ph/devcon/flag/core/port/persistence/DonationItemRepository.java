package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.donations.domain.DonationItem;

public interface DonationItemRepository {
    List<DonationItem> findAllDonationItems();
    List<DonationItem> findByDonationId(long id);
    DonationItem findById(long id);
    DonationItem createUpdateDonationItem(DonationItem packageContent);
    void deleteById(long id);
    void deleteAllDonationItems();
    void deleteByDonationId(long id);
}
