package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.donor.domain.Donor;

public interface DonorRepository {
    List<Donor> findAllDonors();
    Donor createUpdateDonor(Donor donor);
    Donor findById(long id);
    Donor findByMobile(String mobile);
    Donor findDonor(String contactPerson, String affiliation, String email, String mobileNumber, long sectorId);
    List<Donor> findBySector(int sectorTypeId);
}
