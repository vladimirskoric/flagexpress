package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.donations.domain.Donation;
import ph.devcon.flag.core.port.persistence.DonationRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PanacheDonationRepository implements DonationRepository, PanacheRepository<Donation> {

    @Inject
    protected EntityManager em;

    @Override
    public Donation findById(long id) {
        return find("id", id).firstResult();
    }

    @Override
    public Donation createUpdateDonation(Donation pkg) {

        if (pkg.getId() != 0)
            em.merge(pkg);
        else
            persist(pkg);

        flush();
        return pkg;
    }

    @Override
    public List<Donation> findAllDonations() {
        return listAll(Sort.by("id"));
    }

    @Override
    public List<Donation> findByDonationRequestId(long donationRequestId) {
        return list("donation_request_id", donationRequestId);
    }

    @Override
    public void deleteById(long id) {
      delete("id",id);
    }

    @Override
    public void deleteAllDonations() {
      deleteAll();
    }

    @Override
    public void deleteByDonationRequestId(long donationRequestId) {
       delete("donation_request_id", donationRequestId);
    }
}
