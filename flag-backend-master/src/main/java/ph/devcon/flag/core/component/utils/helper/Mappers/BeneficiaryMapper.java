package ph.devcon.flag.core.component.utils.helper.Mappers;

import java.util.ArrayList;
import java.util.List;

import ph.devcon.flag.core.component.beneficiary.application.BeneficiaryDTO;
import ph.devcon.flag.core.component.beneficiary.domain.Beneficiary;

public final class BeneficiaryMapper{
     
    public static List<BeneficiaryDTO> toBeneficiariesDTO(List<Beneficiary> beneficiaries) {
        List<BeneficiaryDTO> dtos = new ArrayList<BeneficiaryDTO>();
        for(Beneficiary item: beneficiaries){
            BeneficiaryDTO dto = toBeneficiaryDTO(item);
            dtos.add(dto);
        }

        return dtos;
    }

    public static BeneficiaryDTO toBeneficiaryDTO(Beneficiary beneficiary){
        if(beneficiary == null)
            return new BeneficiaryDTO();
            
        BeneficiaryDTO dto = BeneficiaryDTO.builder()
                                .id(beneficiary.getId())
                                .contactPerson(beneficiary.getContactPerson())
                                .affiliation(beneficiary.getAffiliation())
                                .mobileNumber(beneficiary.getMobileNumber())
                                .landlineNUmber(beneficiary.getLandlineNuber())
                                .emailAddress(beneficiary.getEmailAddress())
                                .sectorType(beneficiary.getSector().getName())
                                .address(AddressMapper.toAddressDTO(beneficiary.getAddress()))
                                .build();

        return dto;
    }
}