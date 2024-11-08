package ph.devcon.flag.core.component.beneficiary.application;

import ph.devcon.flag.core.component.address.application.AddressDTO;
import ph.devcon.flag.core.component.address.domain.Address;
import ph.devcon.flag.core.component.beneficiary.domain.Beneficiary;
import ph.devcon.flag.core.component.beneficiary.domain.BeneficiarySector;
import ph.devcon.flag.core.component.exception.InvalidRequestException;
import ph.devcon.flag.core.component.utils.helper.Mappers.BeneficiaryMapper;
import ph.devcon.flag.core.port.persistence.BeneficiaryRepository;
import ph.devcon.flag.core.port.persistence.BeneficiarySectorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DefaultBeneficiaryService implements BeneficiaryService {

    @Inject
    BeneficiaryRepository beneficiaryRepository;

    @Inject
    BeneficiarySectorRepository sectorRepository;
    
    @Override
    public BeneficiaryDTO getBeneficiaryById(long id) {
        return BeneficiaryMapper.toBeneficiaryDTO(beneficiaryRepository.findById(id));
    }

    @Override
    public List<BeneficiaryDTO> getAllBeneficiaries() {
        List<Beneficiary> beneficiaries = beneficiaryRepository.findAllBeneficiaries();
        return BeneficiaryMapper.toBeneficiariesDTO(beneficiaries);
    }

    @Override
    public List<BeneficiaryDTO> getByMobileNumber(String mobileNumber) {
        List<Beneficiary> beneficiaries  = beneficiaryRepository.findByMobileNumber(mobileNumber);
        return BeneficiaryMapper.toBeneficiariesDTO(beneficiaries);
    }

    @Override
    public List<BeneficiaryDTO> getByEmailAddress(String emailAddress) {
        List<Beneficiary> beneficiaries  = beneficiaryRepository.findByEmailAddress(emailAddress);
        return BeneficiaryMapper.toBeneficiariesDTO(beneficiaries);
    }

    @Override
    public Beneficiary createUpdateBeneficiaryInternal(final BeneficiaryDTO beneficiary){
        Beneficiary newBeneficiary = newBeneficiary(beneficiary);
        return beneficiaryRepository.createUpdateBeneficiary(newBeneficiary);
    }

    @Override
    public void createUpdateBeneficiary(BeneficiaryDTO beneficiary){ 
        Beneficiary newBeneficiary = newBeneficiary(beneficiary);
        beneficiaryRepository.createUpdateBeneficiary(newBeneficiary);
    }

    private Beneficiary newBeneficiary(BeneficiaryDTO dto){

        if(dto == null)
            return null;
            
        String affiliation = dto.getAffiliation();
        String name = dto.getContactPerson();
        
        if(affiliation == null || affiliation.isEmpty() && name == null || name.isEmpty()) 
        throw new InvalidRequestException("Beneficiary must have at least a name or affiliation.");

        BeneficiarySector sector = sectorRepository.findByCode(dto.getSectorType());
        if(sector == null)
            throw new InvalidRequestException("Invalid beneficiary sector.");

        long id = dto.getId();
        Beneficiary beneficiary = beneficiaryRepository.findById(id);
        beneficiary = beneficiary == null? new Beneficiary() : beneficiary;
        beneficiary.setContactPerson(dto.getContactPerson());
        beneficiary.setAffiliation(dto.getAffiliation());
        beneficiary.setSector(sector);
        beneficiary.setEmailAddress(dto.getEmailAddress());
        beneficiary.setMobileNumber(dto.getMobileNumber());
        beneficiary.setLandlineNuber(dto.getLandlineNUmber());
     
        Address address = beneficiary.getAddress();
        address = address == null? new Address() : address;

        AddressDTO addressDTO = dto.getAddress();
        address.setAddressLineOne(addressDTO.getAddressLineOne());
        address.setBarangay(addressDTO.getBarangay());
        address.setCity(addressDTO.getCity());
        address.setProvince(addressDTO.getProvince());
        address.setCountry(addressDTO.getCountry());
        address.setRegion(addressDTO.getRegion());
        beneficiary.setAddress(address);
    
        return beneficiary;
    }
}
