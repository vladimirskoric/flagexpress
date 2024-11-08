package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.donationrequest.domain.DonationRequest;
import ph.devcon.flag.core.port.persistence.DonationRequestRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@ApplicationScoped
public class PanacheDonationRequestRepository implements DonationRequestRepository, PanacheRepository<DonationRequest> {
    @Inject
    protected EntityManager em;

    @Override
    public DonationRequest createUpdateDonationRequest(DonationRequest donationRequest) {
        if(donationRequest.getId() != 0)
            em.merge(donationRequest);
        else
            persist(donationRequest);

        flush();
        return donationRequest;
    }

    @Override
    public DonationRequest findDonationRequest(Long donationRequestId) {
        return find("id", donationRequestId).firstResult();
    }

    @Override
    public DonationRequest findDonationRequestByRefCode(String refCode) {
        return find("ref_code", refCode).firstResult();
    }

    @Override
    public List<DonationRequest> findDonationRequestByDonorId(Long donorId) {
        return find("donor_id", donorId, Sort.by("id")).list();
    }

    @Override
    public List<DonationRequest> getAllDonationRequests() {
        return listAll(Sort.by("id"));
    }
    
    @Override
    public void DeleteAllDonationRequests() {
       deleteAll();
    }

    @Override
    public void DeleteDonationRequestById(long id) {
        delete("id", id);
    }

    @Override
    public List<DonationRequest> getAllDonationRequestsByDate(LocalDateTime start, LocalDateTime end) {
        List<DonationRequest> requests= list("created_date >= :start and created_date <= :end", Parameters.with("start", start).and("end", end));
        return requests;
    }
}
