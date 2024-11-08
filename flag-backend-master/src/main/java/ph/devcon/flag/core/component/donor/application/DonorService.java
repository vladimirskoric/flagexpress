package ph.devcon.flag.core.component.donor.application;

import java.util.List;

import ph.devcon.flag.core.component.donor.domain.Donor;

public interface DonorService {
    void createUpdateDonor(DonorDTO donor) throws Exception;
    Donor createUpdateDonorInternal(DonorDTO dto) throws Exception;
    List<DonorDTO> getDonors();
    DonorDTO getDonorById(long id);
    List<DonorDTO> getDonorsBySector(int sectorId);
    DonorDTO getDonorByMobile(String mobileNumber);
}
