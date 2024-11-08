package ph.devcon.flag.core.component.beneficiary.application;

import java.util.List;

import ph.devcon.flag.core.component.beneficiary.domain.Beneficiary;

public interface BeneficiaryService {
    BeneficiaryDTO getBeneficiaryById(long id);
    List<BeneficiaryDTO> getAllBeneficiaries();
    List<BeneficiaryDTO> getByMobileNumber(final String mobileNumber);
    List<BeneficiaryDTO> getByEmailAddress(final String emailAddress);
    void createUpdateBeneficiary(final BeneficiaryDTO beneficiary);
    Beneficiary createUpdateBeneficiaryInternal(final BeneficiaryDTO beneficiary);
}
