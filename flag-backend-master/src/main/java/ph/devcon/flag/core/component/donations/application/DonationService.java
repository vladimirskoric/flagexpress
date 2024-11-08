package ph.devcon.flag.core.component.donations.application;

import ph.devcon.flag.core.component.donationrequest.domain.DonationRequest;
import ph.devcon.flag.core.component.donations.domain.Donation;
import ph.devcon.flag.core.component.utils.domain.Currency;
import ph.devcon.flag.core.component.utils.domain.DonationType;
import ph.devcon.flag.core.component.utils.domain.DonationUnit;

import java.util.List;

public interface DonationService {
    List<DonationDTO> getDonations();
    List<DonationDTO> getDonationsByRequestId(long donationRequestId);
    DonationDTO getDonation(long id);
    void createUpdateDonation(DonationDTO donation);
    Donation createUpdateDonation(DonationDTO donation, DonationRequest request);
    Currency getCurrencyByCode(String code);
    DonationUnit getDonationUnitByCode(String unit);
    DonationType getDonationTypeByCode(String code);
    void deleteDonationById(long id);
    void deleteDonationByRequestId(long id);
    void deleteAllDonations();
}