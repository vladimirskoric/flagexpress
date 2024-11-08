package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.donations.domain.CashDonation;

public interface CashDonationRepository {
    List<CashDonation> findAllCashDonations();
    List<CashDonation> findByDonationId(long id);
    CashDonation findById(long id);
    CashDonation createUpdateCashDonation(CashDonation cashDonation);
    void deleteById(long id);
    void deleteAllCashDonations();
    void deleteByDonationId(long id);
}
