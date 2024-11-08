package ph.devcon.flag.core.component.donationrequest.application;
import ph.devcon.flag.core.component.donationrequest.domain.RequestType;

import java.util.List;

public interface DonationRequestService {
    DonationRequestRefCodeDTO createUpdateDonationRequest(DonationRequestDTO donationRequestDTO) throws Exception;
    DonationRequestDTO getDonationRequestById(Long donationRequestId);
    List<DonationRequestDTO> getDonationRequestByDonorId(Long donorId);
    List<DonationRequestDTO> getDonationRequests();
    RequestType getRequestTypeByName(String requestType);
    void DeleteDonationRequests();
    void DeleteDonationRequestById(long id);
}
