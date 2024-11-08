package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.donations.domain.Service;

public interface ServiceDonationRepository {
    List<Service> findAllServiceDonations();
    List<Service> findByDonationId(long id);
    Service findById(long id);
    Service createUpdateServiceDonation(Service serviceDonation);
    void deleteById(long id);
    void deleteAllServiceDonations();
    void deleteByDonationId(long id);
}
