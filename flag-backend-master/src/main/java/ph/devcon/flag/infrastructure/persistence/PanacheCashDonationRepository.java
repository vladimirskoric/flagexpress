package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.donations.domain.CashDonation;
import ph.devcon.flag.core.port.persistence.CashDonationRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PanacheCashDonationRepository implements CashDonationRepository, PanacheRepository<CashDonation> {

    @Inject
    protected EntityManager em;

    @Override
    public List<CashDonation> findAllCashDonations(){
       return listAll(Sort.by("id"));
    }

    @Override
    public List<CashDonation> findByDonationId(long id) {
        return list("donation_id", id, Sort.by("id"));
    }

    @Override
    public CashDonation findById(long id) {
        return find("id", id).firstResult();
    }

    @Override
    public CashDonation createUpdateCashDonation(CashDonation cashDonation) {
 
        if (cashDonation.getId() != 0)
            em.merge(cashDonation);
        else
            persist(cashDonation);

        flush();
        return cashDonation;
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
    public void deleteAllCashDonations() {
        deleteAll();
    }
}
