package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.donations.domain.Rental;
import ph.devcon.flag.core.port.persistence.RentalDonationRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PanacheRentalDonationRepository implements RentalDonationRepository, PanacheRepository<Rental> {

    @Inject
    protected EntityManager em;

    @Override
    public List<Rental> findAllRentalDonations(){
       return listAll(Sort.by("id"));
    }

    @Override
    public List<Rental> findByDonationId(long id) {
        return list("donation_id", id);
    }

    @Override
    public Rental findById(long id) {
        return find("id", id).firstResult();
    }

    @Override
    public Rental createUpdateRentalDonation(Rental rentalDonation) {
 
        if (rentalDonation.getId() != 0)
            em.merge(rentalDonation);
        else
            persist(rentalDonation);

        flush();
        return rentalDonation;
    }

    @Override
    public void deleteById(long id) {
        delete("id",id);
    }

    @Override
    public void deleteByDonationId(long id) {
        delete( "donation_id", id);
    }

    @Override
    public void deleteAllRentalDonations() {
        deleteAll();
    }
}
