package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.donations.domain.Donation;

public interface DonationRepository {
    List<Donation> findAllDonations();
    List<Donation> findByDonationRequestId(long donationRequestId);
    Donation findById(long id);
    Donation createUpdateDonation(Donation pkg);
    void deleteById(long id);
    void deleteAllDonations();
    void deleteByDonationRequestId(long donationRequestId);
}
