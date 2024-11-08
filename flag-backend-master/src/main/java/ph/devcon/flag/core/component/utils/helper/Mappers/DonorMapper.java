package ph.devcon.flag.core.component.utils.helper.Mappers;

import java.util.List;
import java.util.stream.Collectors;
import ph.devcon.flag.core.component.donor.application.DonorDTO;
import ph.devcon.flag.core.component.donor.domain.Donor;

public final class DonorMapper {
    
    public static List<DonorDTO> toDonors(List<Donor> donors){
       return donors.stream()
             .map(x->toDonorDTO(x))
             .collect(Collectors.toList());
    }

    public static DonorDTO toDonorDTO(Donor donor) {
        if(donor == null)
            return null;
            
        DonorDTO dto = DonorDTO.builder()
                    .id(donor.getId())
                    .contactPerson(donor.getContactPerson())
                    .affiliation(donor.getAffiliation())
                    .mobileNumber(donor.getMobileNumber())
                    .landlineNumber(donor.getLandlineNumber())
                    .sectorType(donor.getSectorType().getName())
                    .email(donor.getEmail())
                    .isAnonymous(donor.isAnonymous())
                    .isInternational(donor.isInternational())
                    .address(AddressMapper.toAddressDTO(donor.getAddress()))
                    .build();

        return dto;
    }
}