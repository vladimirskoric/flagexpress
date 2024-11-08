package ph.devcon.flag.core.component.donations.application;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ph.devcon.flag.core.component.donationrequest.domain.DonationRequest;
import ph.devcon.flag.core.component.donations.domain.DonationItem;
import ph.devcon.flag.core.component.donations.domain.Rental;
import ph.devcon.flag.core.component.donations.domain.Service;
import ph.devcon.flag.core.component.donations.domain.ServiceType;
import ph.devcon.flag.core.component.donations.domain.CashDonation;
import ph.devcon.flag.core.component.donations.domain.Donation;
import ph.devcon.flag.core.component.exception.InvalidRequestException;
import ph.devcon.flag.core.component.utils.domain.Currency;
import ph.devcon.flag.core.component.utils.domain.DonationType;
import ph.devcon.flag.core.component.utils.domain.DonationUnit;
import ph.devcon.flag.core.component.utils.helper.Mappers.DonationMapper;
import ph.devcon.flag.core.port.filestorage.FileStorageProvider;
import ph.devcon.flag.core.port.persistence.*;


@ApplicationScoped
public class DefaultDonationService implements DonationService {

    @Inject
    DonationRepository donationRepo;

    @Inject
    DonationItemRepository itemRepo;

    @Inject
    CashDonationRepository cashRepo;

    @Inject
    ServiceDonationRepository serviceRepo;

    @Inject
    RentalDonationRepository rentalRepo;

    @Inject
    ServiceTypeRepository serviceTypeRepo;
    
    @Inject
    CurrencyRepository currencyRepo;

    @Inject
    DonationTypeRepository donationTypeRepo;

    @Inject
    UnitRepository unitRepo;

    @Inject
    DonationRequestRepository donationRequestRepository;

    @Inject
    FileStorageProvider fileStorageProvider;

    @Override
    public List<DonationDTO> getDonations() { 
        List<Donation> donations = donationRepo.findAllDonations();
        return DonationMapper.toDonationsDTO(donations);
    }

    @Override
    public List<DonationDTO> getDonationsByRequestId(long donationRequestId) {
        List<Donation> donations =  donationRepo.findByDonationRequestId(donationRequestId);
        return DonationMapper.toDonationsDTO(donations);
    }

    @Override
    public Currency getCurrencyByCode(String code) {
        return currencyRepo.findByCode(code);
    }

    @Override
    public DonationUnit getDonationUnitByCode(String code) {
        return unitRepo.findByCode(code);
    }

    @Override
    public DonationType getDonationTypeByCode(String code) {
        return donationTypeRepo.findByCode(code);
    }

    @Override
    public DonationDTO getDonation(long id) {
        Donation donation = donationRepo.findById(id);
        if(donation == null)
            throw new InvalidRequestException("Donation not found.");

        return DonationMapper.toDonationDTO(donation);
    }

    @Transactional
    @Override
    public void createUpdateDonation(DonationDTO dto){
        try{
            newDonation(dto);
        }catch(Exception e){
            throw e;
        }
    }

    @Transactional
    @Override
    public Donation createUpdateDonation(DonationDTO dto, DonationRequest request){
        try{
            return newDonation(dto, request);
        }catch(Exception e){
            throw e;
        }
    }

    private Donation newDonation(DonationDTO dto){
        return newDonation(dto, null);
    }

    private Donation newDonation(DonationDTO dto, DonationRequest request){
        Donation donation = donationRepo.findById(dto.getId());
        donation = donation == null? new Donation() : donation;
        donation.setRemarks(dto.getRemarks());

        String filename = this.saveImage(dto, request);
        donation.setPhotoReference(filename);
        donation.setDonationDate(dto.getDonationDate());
        donation.setFileReference(dto.getFileReference());
        donation.setDonationRequest(request);

        Currency cur = currencyRepo.findByCode("PHP");//peso default currency
        donation.setCurrency(cur);
        donation = AddDonationItems(donation, dto);
        donation = AddCashDonations(donation, dto);
        donation = AddServicesDonations(donation, dto);
        donation = AddRentalDonations(donation, dto);

        donation = donationRepo.createUpdateDonation(donation);
        return donation;
    }

    /**
     * This saves the file and provides the filename.
     *
     * @param dto
     * @param request
     * @return The filename as stored in the provider.
     */
    public String saveImage(DonationDTO dto, DonationRequest request) {
      return dto.getPhotoReference().length > 0 ? fileStorageProvider.saveFile(dto.getPhotoReference()[0], request.getRefCode()) : null;
    }

    @Transactional
    @Override
    public void deleteDonationById(long id) {
        Donation donation = donationRepo.findById(id);
        if(donation == null) 
            throw new InvalidRequestException(String.format("Donation with id %s not found.", id));

        deleteDonation(donation);
    }

    
    @Override
    public void deleteDonationByRequestId(long id) {
        List<Donation> donations = donationRepo.findByDonationRequestId(id);
        for(Donation item: donations){
            deleteDonation(item);
        }
    }

    @Transactional
    @Override
    public void deleteAllDonations() {    
        itemRepo.deleteAllDonationItems();
        cashRepo.deleteAllCashDonations();
        serviceRepo.deleteAllServiceDonations();
        rentalRepo.deleteAllRentalDonations();
        donationRepo.deleteAllDonations();
    }

    private void deleteDonation(Donation donation){   
        long id = donation.getId();
           
        if(!donation.getItems().isEmpty())
        itemRepo.deleteByDonationId(id);

        if(!donation.getCashDonations().isEmpty())
            cashRepo.deleteByDonationId(id);

        if(!donation.getServiceDonations().isEmpty())
            serviceRepo.deleteByDonationId(id);

        if(!donation.getRentalDonations().isEmpty())
            rentalRepo.deleteByDonationId(id);

        donationRepo.deleteById(id);
    }
    
    private Donation AddDonationItems(Donation donation, DonationDTO dto){
        List<DonationItem> items = new ArrayList<DonationItem>();
        Currency currency = donation.getCurrency();
        double totalValue = donation.getValue();

        DonationUnit unit = null;
        DonationType donationType = null;

        for(DonationItemDTO item:dto.getItems()){

            unit = unitRepo.findByCode(item.getUnit());
            if(unit == null)
                throw new InvalidRequestException(String.format("Invalid unit code %s", item.getUnit()));

            donationType = donationTypeRepo.findByCode(item.getDonationType());
            if(donationType == null)
                throw new InvalidRequestException(String.format("Invalid donation type %s", item.getDonationType()));
   
            currency = currencyRepo.findByCode(item.getCurrency());
            if(currency == null)
                throw new InvalidRequestException(String.format("Invalid currency code %s", item.getCurrency()));

            DonationItem donationItem = itemRepo.findById(item.getId());
            donationItem = donationItem == null? new DonationItem() : donationItem;
            donationItem.setDonationType(donationType);
            donationItem.setCurrency(currency);
            donationItem.setEstimatedCost(item.getEstimatedCost());
            donationItem.setTotalUnitValue(item.getTotalUnitValue());
            donationItem.setUnit(unit);
            donationItem.setDescription(item.getDescription());
            donationItem.setWeight(item.getWeight());
            donationItem.setDonation(donation); 
            items.add(donationItem);

            totalValue += item.getEstimatedCost();      
        }

        donation.setValue(totalValue);
        donation.setItems(items);

        return donation;
    }

    private Donation AddCashDonations(Donation donation, DonationDTO dto){
        List<CashDonation> cashDonations= new ArrayList<CashDonation>();
        Currency cur = donation.getCurrency();
        double totalValue = donation.getValue();
        
        DonationType donationType = donationTypeRepo.findByCode("CASH");

        for(CashDonationDTO item: dto.getCashDonations()){
    
            cur = currencyRepo.findByCode(item.getCurrency());
            if(donationType == null)
                throw new InvalidRequestException(String.format("Invalid currency code %s", item.getCurrency()));

            CashDonation cd = new CashDonation();
            cd.setDonationType(donationType);
            cd.setDonation(donation);
            cd.setCurrency(cur);
            cd.setAmount(item.getAmount());
            cd.setDescription(item.getDescription());
            cashDonations.add(cd);

            totalValue += item.getAmount();
        }
  
        donation.setValue(totalValue);
        donation.setCashDonations(cashDonations);

        return donation;
    }

    private Donation AddServicesDonations(Donation donation, DonationDTO dto) {
        List<Service> services = new ArrayList<Service>();
        Currency currency = donation.getCurrency();
        double totalValue = donation.getValue();
        
        ServiceType serviceType = null;
        DonationType donationType = donationTypeRepo.findByCode("CASH");

        for(ServiceDTO item: dto.getServices()){

            currency = currencyRepo.findByCode(item.getCurrency());
            if(currency == null)
                throw new InvalidRequestException(String.format("Invalid currency code %s", item.getCurrency()));

            serviceType = serviceTypeRepo.findByCode(item.getServiceType());
            if(serviceType == null)
                throw new InvalidRequestException(String.format("Invalid service type %s", item.getServiceType()));
            
            Service svc = new Service();
            svc.setServiceType(serviceType);
            svc.setCurrency(currency);
            svc.setDonation(donation);
            svc.setDonationType(donationType);
            svc.setDescription(item.getDescription());
            svc.setEstimatedCost(item.getEstimatedCost());
            svc.setHours(item.getHours());
            svc.setStartDate(item.getStartDate());
            svc.setEndDate(item.getEndDate());
            services.add(svc);

            totalValue += item.getEstimatedCost();
        }

        donation.setValue(totalValue);
        donation.setServiceDonations(services);
        return donation;
    }

    private Donation AddRentalDonations(Donation donation, DonationDTO dto) {
        List<Rental> rentals = new ArrayList<Rental>();
        Currency currency = donation.getCurrency();
        double totalValue = donation.getValue();
        
        DonationType donationType = donationTypeRepo.findByCode("RENT");

        for(RentalDTO item: dto.getRentals()){

            currency = currencyRepo.findByCode(item.getCurrency());
            if(currency == null)
                throw new InvalidRequestException(String.format("Invalid currency code %s", item.getCurrency()));

            
            Rental rental = new Rental();
            rental.setCurrency(currency);
            rental.setDonation(donation);
            rental.setDonationType(donationType);
            rental.setDescription(item.getDescription());
            rental.setEstimatedCost(item.getEstimatedCost());
            rental.setStartDate(item.getStartDate());
            rental.setEndDate(item.getEndDate());
            rentals.add(rental);

            totalValue += item.getEstimatedCost();
        }

        donation.setValue(totalValue);
        donation.setRentalDonations(rentals);
        return donation;
    }

}