package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.donations.domain.DonationItem;
import ph.devcon.flag.core.port.persistence.DonationItemRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PanacheDonationItemRepository implements DonationItemRepository, PanacheRepository<DonationItem> {

    @Inject
    protected EntityManager em;

    @Override
    public List<DonationItem> findAllDonationItems() {
       return listAll(Sort.by("id"));
    }

    @Override
    public List<DonationItem> findByDonationId(long id) {
        return list("donation_id", id);
    }

    @Override
    public DonationItem findById(long id) {
        return find("id", id).firstResult();
    }

    @Override
    public DonationItem createUpdateDonationItem(DonationItem packageContent) {
 
        if (packageContent.getId() != 0)
            em.merge(packageContent);
        else
            persist(packageContent);

        flush();
        return packageContent;
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
    public void deleteAllDonationItems() {
        deleteAll();
    }
}
