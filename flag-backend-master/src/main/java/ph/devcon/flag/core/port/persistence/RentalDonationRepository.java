package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.donations.domain.Rental;

public interface RentalDonationRepository {
    List<Rental> findAllRentalDonations();
    List<Rental> findByDonationId(long id);
    Rental findById(long id);
    Rental createUpdateRentalDonation(Rental rentalDonation);
    void deleteById(long id);
    void deleteAllRentalDonations();
    void deleteByDonationId(long id);
}
