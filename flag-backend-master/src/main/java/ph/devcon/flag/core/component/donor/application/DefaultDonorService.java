package ph.devcon.flag.core.component.donor.application;

import ph.devcon.flag.core.component.address.domain.Address;
import ph.devcon.flag.core.component.address.application.AddressDTO;
import ph.devcon.flag.core.component.donor.domain.Donor;
import ph.devcon.flag.core.component.donor.domain.SectorType;
import ph.devcon.flag.core.component.exception.InvalidRequestException;
import ph.devcon.flag.core.component.utils.helper.Mappers.DonorMapper;
import ph.devcon.flag.core.port.persistence.AddressRepository;
import ph.devcon.flag.core.port.persistence.DonorRepository;
import ph.devcon.flag.core.port.persistence.DonorSectorRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class DefaultDonorService implements DonorService {

    @Inject
    DonorRepository donorRepository;

    @Inject
    DonorSectorRepository sectorRepository;

    @Inject
    AddressRepository addressRepository;
    
    @Override
    public DonorDTO getDonorById(long id) {
        Donor donor = donorRepository.findById(id);
        return DonorMapper.toDonorDTO(donor);
    }

    @Override
    public List<DonorDTO> getDonors() {
        List<Donor> donors = donorRepository.findAllDonors();
        return DonorMapper.toDonors(donors);
    }

    @Override
    public List<DonorDTO> getDonorsBySector(int sectorId) {
        List<Donor> donors = donorRepository.findBySector(sectorId);
        return DonorMapper.toDonors(donors);
    }

    @Override
    public DonorDTO getDonorByMobile(String mobileNumber) {
        Donor donor = donorRepository.findByMobile(mobileNumber);
        return DonorMapper.toDonorDTO(donor);
    }

    @Transactional
    @Override
    public void createUpdateDonor(DonorDTO dto) throws Exception {

        String affiliation = dto.getAffiliation();
        String name = dto.getContactPerson();
        
        if(affiliation == null || affiliation.isEmpty() && name == null || name.isEmpty()) 
            throw new InvalidRequestException("Donor must have at least a name or affiliation.");

        SectorType sector = sectorRepository.findByCode(dto.getSectorType());
        if(sector == null)
            throw new InvalidRequestException(String.format("Invalid donor sector %s.", dto.getSectorType()));

        Donor donor = newDonor(dto, sector);
        donor.setSectorType(sector);
 
        Address address = addressRepository.createAddress(donor.getAddress());
        donor.setAddress(address);
        
        donorRepository.createUpdateDonor(donor);
    }

    @Transactional
    @Override
     public Donor createUpdateDonorInternal(DonorDTO dto) throws Exception {

        String affiliation = dto.getAffiliation();
        String name = dto.getContactPerson();
        
        if(affiliation == null || affiliation.isEmpty() && name == null || name.isEmpty()) 
            throw new InvalidRequestException("Donor must have at least a name or affiliation.");

        SectorType sector = sectorRepository.findByCode(dto.getSectorType());
        if(sector == null)
            throw new InvalidRequestException(String.format("Invalid donor sector %s.", dto.getSectorType()));

        Donor donor = newDonor(dto, sector);
        donor = donorRepository.createUpdateDonor(donor);
        return donor;
    }

    private Donor newDonor(DonorDTO dto,SectorType sector){
        long id = dto.getId();
        Donor donor = donorRepository.findById(id);
        if(donor == null) {
            donor = donorRepository.findDonor(dto.getContactPerson(),dto.getAffiliation(),dto.getEmail(), dto.getMobileNumber(), sector.getId());
            
            log.info("Donor {}", donor);
            donor = (donor == null)? new Donor() : donor;
            id = (donor == null)? id: donor.getId();

            log.info("Id {}", id);
        }else
        {
            donor.setId(id);
        }

        donor.setSectorType(sector);
        donor.setContactPerson(dto.getContactPerson());
        donor.setAffiliation(dto.getAffiliation());
        donor.setEmail(dto.getEmail());
        donor.setMobileNumber(dto.getMobileNumber());
        donor.setLandlineNumber(dto.getLandlineNumber());
        donor.setAnonymous(dto.isAnonymous());
        donor.setInternational(dto.isInternational());
        
        Address address = donor.getAddress();
        address = address == null? new Address() : address;

        AddressDTO addressDTO = dto.getAddress();
        address.setAddressLineOne(addressDTO.getAddressLineOne());
        address.setBarangay(addressDTO.getBarangay());
        address.setCity(addressDTO.getCity());
        address.setProvince(addressDTO.getProvince());
        address.setCountry(addressDTO.getCountry());
        address.setRegion(addressDTO.getRegion());

        address = addressRepository.createAddress(address);
        donor.setAddress(address);
    
        return donor;
    }
}
