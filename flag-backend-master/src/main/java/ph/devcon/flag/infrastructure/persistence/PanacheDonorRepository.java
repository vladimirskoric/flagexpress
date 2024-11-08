package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import lombok.extern.slf4j.Slf4j;
import ph.devcon.flag.core.component.donor.domain.Donor;
import ph.devcon.flag.core.port.persistence.DonorRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Slf4j
@ApplicationScoped
public class PanacheDonorRepository implements DonorRepository, PanacheRepository<Donor> {

    @Inject
    protected EntityManager em;
    
    @Override
    public List<Donor> findAllDonors() {
        return listAll(Sort.by("id"));
    }

    @Override
    public Donor findById(final long id) {
        return find("id", id).firstResult();
    }

    @Override
    public Donor findByMobile(final String mobile) {
        return find("mobile_number", mobile).firstResult();
    }

    @Override
    public List<Donor> findBySector(int sectorTypeId) {
        return list("sector_type_id", sectorTypeId, Sort.by("id"));
    }

    @Override
    public Donor createUpdateDonor(Donor donor) {
        
        if(donor.getId() != 0)
            em.merge(donor);
        else
            persist(donor);
            
        flush();
        return donor;
    }

    @Override
    public Donor findDonor(String contactPerson, String affiliation, String email, String mobileNumber, long sectorId) {

        log.info("cp={},affiliation={},mobileNumber={},email={},sectorId={}",contactPerson,affiliation,mobileNumber,email,sectorId);
        return find("contact_person = :cp and affiliation_org = :affiliation and email_address = :email and mobile_number = :mobile and sector_type_id =:sectorId",
                Parameters.with("cp", contactPerson)
               .and("affiliation", affiliation)
               .and("email", email)
               .and("mobile", mobileNumber)
               .and("sectorId", sectorId)).firstResult();
    }
}
