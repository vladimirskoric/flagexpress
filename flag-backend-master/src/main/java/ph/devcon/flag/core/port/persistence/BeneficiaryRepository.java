package ph.devcon.flag.core.port.persistence;

import ph.devcon.flag.core.component.beneficiary.domain.Beneficiary;

import java.util.List;

public interface BeneficiaryRepository {
    Beneficiary findById(long id);
    List<Beneficiary> findAllBeneficiaries();
    List<Beneficiary> findByMobileNumber(String mobileNumber);
    List<Beneficiary> findByEmailAddress(String emailAddress);
    Beneficiary createUpdateBeneficiary(Beneficiary beneficiary);
}
