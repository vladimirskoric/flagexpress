package ph.devcon.flag.core.port.persistence;

import ph.devcon.flag.core.component.donationrequest.domain.DonationRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface DonationRequestRepository {
    DonationRequest createUpdateDonationRequest(DonationRequest donationRequest);
    DonationRequest findDonationRequest(Long donationRequestId);
    DonationRequest findDonationRequestByRefCode(String refCode);
    List<DonationRequest> findDonationRequestByDonorId(Long donorId);
    List<DonationRequest> getAllDonationRequests();
    void DeleteAllDonationRequests();
    void DeleteDonationRequestById(long id);
    List<DonationRequest> getAllDonationRequestsByDate(LocalDateTime start, LocalDateTime end);
}