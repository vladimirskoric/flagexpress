package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.beneficiary.domain.Beneficiary;
import ph.devcon.flag.core.port.persistence.BeneficiaryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PanacheBeneficiaryRepository implements BeneficiaryRepository, PanacheRepository<Beneficiary> {

    @Inject
    protected EntityManager em;

    @Override
    public Beneficiary findById(long id) {
        return find("id", id).firstResult();
    }

    @Override
    public List<Beneficiary> findAllBeneficiaries(){
        return listAll(Sort.by("id"));
    }

    @Override
    public List<Beneficiary> findByMobileNumber(String mobileNumber) {
        return find("mobile_number",mobileNumber, Sort.by("id")).firstResult();
    }

    @Override
    public List<Beneficiary> findByEmailAddress(String emailAddress) {
        return find("email_address", emailAddress, Sort.by("id")).firstResult();
    }

    @Override
    public Beneficiary createUpdateBeneficiary(Beneficiary beneficiary) {
        if(beneficiary.getId() != 0)
            em.merge(beneficiary);
        else
            persist(beneficiary);

        flush();
        return beneficiary;
    }
}
