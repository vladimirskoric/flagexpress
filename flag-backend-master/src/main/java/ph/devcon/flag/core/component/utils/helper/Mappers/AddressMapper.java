package ph.devcon.flag.core.component.utils.helper.Mappers;

import ph.devcon.flag.core.component.address.domain.Address;
import ph.devcon.flag.core.component.address.application.AddressDTO;

public final class AddressMapper {

    public static AddressDTO toAddressDTO(Address address) {
        if(address != null){
            return AddressDTO.builder()
                            .id(address.getId())
                            .addressLineOne(address.getAddressLineOne())
                            .barangay(address.getBarangay())
                            .city(address.getCity())
                            .province(address.getProvince())
                            .country(address.getCountry())
                            .region(address.getRegion())
                            .build();
        }
        
        return null;
    }

    public static Address toAddress(AddressDTO dto){
        Address address = new Address();
        address.setAddressLineOne(dto.getAddressLineOne());
        address.setBarangay(dto.getBarangay());
        address.setCity(dto.getCity());
        address.setProvince(dto.getProvince());
        address.setCountry(dto.getCountry());
        address.setRegion(dto.getRegion());
        
        return address;
    }
}
