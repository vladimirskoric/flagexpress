package ph.devcon.flag.core.component.donationrequest.application;

import lombok.extern.slf4j.Slf4j;
import ph.devcon.flag.core.component.beneficiary.application.BeneficiaryDTO;
import ph.devcon.flag.core.component.beneficiary.application.BeneficiaryService;
import ph.devcon.flag.core.component.beneficiary.domain.Beneficiary;
import ph.devcon.flag.core.component.donationrequest.domain.*;
import ph.devcon.flag.core.component.donations.application.*;
import ph.devcon.flag.core.component.donor.application.*;
import ph.devcon.flag.core.component.donor.domain.*;
import ph.devcon.flag.core.component.exception.InvalidRequestException;
import ph.devcon.flag.core.component.utils.application.RefCodeGeneratorService;
import ph.devcon.flag.core.component.utils.helper.Mappers.*;
import ph.devcon.flag.core.port.persistence.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@ApplicationScoped
public class DefaultDonationRequestService implements DonationRequestService{
    
    @Inject
    DonationRequestRepository donationRequestRepository;

    @Inject
    DonationService donationService;

    @Inject
    RequestTypeRepository requestTypeRepository;

    @Inject
    DonorSectorRepository sectorRepository;

    @Inject
    DonorService donorService;

    @Inject
    BeneficiaryService beneficiaryService;

    @Inject
    DonationService packageService;

    @Inject
    RefCodeGeneratorService<DonationRequest> refCodeGeneratorService;

    @Override
    public DonationRequestDTO getDonationRequestById(Long donationRequestId) {
        return toDonationRequestDTO(donationRequestRepository.findDonationRequest(donationRequestId));
    }

    @Override
    public List<DonationRequestDTO> getDonationRequestByDonorId(Long donorId) {
        List<DonationRequest> requests = donationRequestRepository.findDonationRequestByDonorId(donorId);
        return requests.stream()
                .map(x-> toDonationRequestDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public RequestType getRequestTypeByName(String requestType) {
        return requestTypeRepository.findByName(requestType);
    }

    @Override
    @Transactional
    public DonationRequestRefCodeDTO createUpdateDonationRequest(DonationRequestDTO donationRequestDTO) throws Exception {
        DonationRequest donationRequest = newDonationRequest(donationRequestDTO);
        donationRequest.setRefCode(refCodeGeneratorService.generateRefCode());
        donationRequest = donationRequestRepository.createUpdateDonationRequest(donationRequest);
        log.info("Donation Request ID: " + donationRequest.getId());

        for(DonationDTO donation: donationRequestDTO.getDonations()){
            packageService.createUpdateDonation(donation, donationRequest);   
        }

        return DonationRequestRefCodeDTO.builder()
                .refCode(donationRequest.getRefCode())
                .build();
    }

    private DonationRequest newDonationRequest(DonationRequestDTO donationRequestDTO) throws Exception {  
        RequestType requestType = requestTypeRepository.findByName(donationRequestDTO.getRequestType());

        if(requestType == null)
            throw new InvalidRequestException(format("RequestType %s not found.", donationRequestDTO.getRequestType()));

   
        DonationRequest donationRequest = new DonationRequest();
        donationRequest.setRequestType(requestType);
        donationRequest.setCreatedDate(LocalDateTime.now(ZoneOffset.UTC));
        donationRequest.setDateLastModified(LocalDateTime.now(ZoneOffset.UTC));

        Donor donor = donorService.createUpdateDonorInternal(donationRequestDTO.getDonorDTO());
        donationRequest.setDonor(donor);

        BeneficiaryDTO benDTo = donationRequestDTO.getBeneficiaryDTO();
        if(benDTo!=null){
            Beneficiary beneficiary = beneficiaryService.createUpdateBeneficiaryInternal(benDTo);
            donationRequest.setBeneficiary(beneficiary);
        }
        
        return donationRequest;
    }
  
    @Override
    public List<DonationRequestDTO> getDonationRequests() {
       List<DonationRequest> requests = donationRequestRepository.getAllDonationRequests();
       return requests.stream()
             .map(x-> toDonationRequestDTO(x))
             .collect(Collectors.toList());
    }

    public DonationRequestDTO toDonationRequestDTO(DonationRequest request){
        DonationRequestDTO dto = DonationRequestDTO.builder()
                                 .id(request.getId())
                                 .donorDTO(DonorMapper.toDonorDTO(request.getDonor()))
                                 .beneficiaryDTO(BeneficiaryMapper.toBeneficiaryDTO(request.getBeneficiary()))
                                 .donations(DonationMapper.toDonationsDTO(request.getDonations()))
                                 .requestType(request.getRequestType().getName())
                                 .lastModified(request.getDateLastModified())
                                 .createdDate(request.getCreatedDate())
                                 .build();
         
        return dto;
    }

    @Override
    @Transactional
    public void DeleteDonationRequests() {
       donationService.deleteAllDonations();
       donationRequestRepository.DeleteAllDonationRequests(); 
    }

    @Override
    @Transactional
    public void DeleteDonationRequestById(long id) {
        donationService.deleteDonationByRequestId(id);
        donationRequestRepository.DeleteDonationRequestById(id);
    }
}
