package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.donations.domain.Service;
import ph.devcon.flag.core.port.persistence.ServiceDonationRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PanacheServiceDonationRepository implements ServiceDonationRepository, PanacheRepository<Service> {

    @Inject
    protected EntityManager em;

    @Override
    public List<Service> findAllServiceDonations(){
       return listAll(Sort.by("id"));
    }

    @Override
    public List<Service> findByDonationId(long id) {
        return list("donation_id", id);
    }

    @Override
    public Service findById(long id) {
        return find("id", id).firstResult();
    }

    @Override
    public Service createUpdateServiceDonation(Service serviceDonation) {
 
        if (serviceDonation.getId() != 0)
            em.merge(serviceDonation);
        else
            persist(serviceDonation);

        flush();
        return serviceDonation;
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
    public void deleteAllServiceDonations() {
        deleteAll();
    }
}
