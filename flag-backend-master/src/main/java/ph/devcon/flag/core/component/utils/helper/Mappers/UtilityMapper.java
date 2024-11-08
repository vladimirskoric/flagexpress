package ph.devcon.flag.core.component.utils.helper.Mappers;

import java.util.List;
import java.util.stream.Collectors;

import ph.devcon.flag.core.component.beneficiary.application.BeneficiarySectorDTO;
import ph.devcon.flag.core.component.beneficiary.domain.BeneficiarySector;
import ph.devcon.flag.core.component.donationrequest.application.RequestTypeDTO;
import ph.devcon.flag.core.component.donationrequest.domain.RequestType;
import ph.devcon.flag.core.component.donations.domain.ServiceType;
import ph.devcon.flag.core.component.donor.domain.SectorType;
import ph.devcon.flag.core.component.utils.application.CountryDTO;
import ph.devcon.flag.core.component.utils.application.CurrencyDTO;
import ph.devcon.flag.core.component.utils.application.DonationTypeDTO;
import ph.devcon.flag.core.component.utils.application.DonationUnitDTO;
import ph.devcon.flag.core.component.utils.application.ItemDTO;
import ph.devcon.flag.core.component.utils.application.MunicipalityDTO;
import ph.devcon.flag.core.component.utils.application.ProvinceDTO;
import ph.devcon.flag.core.component.utils.application.RegionDTO;
import ph.devcon.flag.core.component.utils.application.SectorTypeDTO;
import ph.devcon.flag.core.component.utils.application.ServiceTypeDTO;
import ph.devcon.flag.core.component.utils.domain.Country;
import ph.devcon.flag.core.component.utils.domain.Currency;
import ph.devcon.flag.core.component.utils.domain.DonationType;
import ph.devcon.flag.core.component.utils.domain.DonationUnit;
import ph.devcon.flag.core.component.utils.domain.Item;
import ph.devcon.flag.core.component.utils.domain.Municipality;
import ph.devcon.flag.core.component.utils.domain.Province;
import ph.devcon.flag.core.component.utils.domain.Region;

public final class UtilityMapper {
    
    public static List<CurrencyDTO> toCurrenciesDTO(List<Currency> currencies){
        return currencies.stream()
                .map(x-> toCurrencyDTO(x))
                .collect(Collectors.toList());
    }

    public static CurrencyDTO toCurrencyDTO(Currency currency){
        return CurrencyDTO.builder()
                .id(currency.getId())
                .code(currency.getCode())
                .name(currency.getName())
                .build();
    }

    public static List<DonationTypeDTO> toDonationTypesDTO(List<DonationType> donationTypes){
        return donationTypes.stream()
                .map(x-> toDonationTypeDTO(x))
                .collect(Collectors.toList());
    }

    public static DonationTypeDTO toDonationTypeDTO(DonationType donationType){
        return DonationTypeDTO.builder()
                .id(donationType.getId())
                .code(donationType.getCode())
                .name(donationType.getName())
                .build();
    }

    public static List<SectorTypeDTO> toSectorTypesDTO(List<SectorType> sectorTypes){
        return sectorTypes.stream()
                .map(x-> toSectorTypeDTO(x))
                .collect(Collectors.toList());
    }

    public static SectorTypeDTO toSectorTypeDTO(SectorType sectorType){
        return SectorTypeDTO.builder()
                .id(sectorType.getId())
                .code(sectorType.getCode())
                .name(sectorType.getName())
                .build();
    }

    public static List<DonationUnitDTO> toUnitsDTO(List<DonationUnit> units){
        return units.stream()
                .map(x-> toUnitDTO(x))
                .collect(Collectors.toList());
    }

    public static DonationUnitDTO toUnitDTO(DonationUnit unit){
        return DonationUnitDTO.builder()
                .id(unit.getId())
                .code(unit.getCode())
                .name(unit.getName())
                .build();
    }

    public static List<ServiceTypeDTO> toServiceTypesDTO(List<ServiceType> serviceTypes){
        return serviceTypes.stream()
                .map(x-> toServiceTypeDTO(x))
                .collect(Collectors.toList());
    }

    public static ServiceTypeDTO toServiceTypeDTO(ServiceType svc){
        return ServiceTypeDTO.builder()
                .id(svc.getId())
                .code(svc.getCode())
                .name(svc.getName())
                .description(svc.getDescription())
                .build();
    }

    public static List<BeneficiarySectorDTO> toBeneficiarySectorsDTO(List<BeneficiarySector> sectors){
        return sectors.stream()
                .map(x-> toBeneficiarySectorDTO(x))
                .collect(Collectors.toList());
    }

    public static BeneficiarySectorDTO toBeneficiarySectorDTO(BeneficiarySector sector){
        return BeneficiarySectorDTO.builder()
                .id(sector.getId())
                .code(sector.getCode())
                .name(sector.getName())
                .build();
    }

    public static List<RequestTypeDTO> toRequestTypesDTO(List<RequestType> requestTypes){
        return requestTypes.stream()
                .map(x-> toRequestTypeDTO(x))
                .collect(Collectors.toList());
    }

    public static RequestTypeDTO toRequestTypeDTO(RequestType rqType){
        return RequestTypeDTO.builder()
                .id(rqType.getId())
                .name(rqType.getName())
                .description(rqType.getDescription())
                .build();
    }

    public static List<ItemDTO> toItemsDTO(List<Item> items){
        return items.stream()
                .map(x-> toItemDTO(x))
                .collect(Collectors.toList());
    }

    public static ItemDTO toItemDTO(Item item){
        return ItemDTO.builder()
                .id(item.getId())
                .code(item.getCode())
                .name(item.getName())
                .unit(item.getUnit())
                .group(item.getGroup())
                .build();
    }

    
    public static List<RegionDTO> toRegionsDTO(List<Region> regions){
        return regions.stream()
                .map(x-> toRegionDTO(x))
                .collect(Collectors.toList());
    }

    public static RegionDTO toRegionDTO(Region region){
        return RegionDTO.builder()
                .id(region.getId())
                .code(region.getCode())
                .name(region.getName())
                .build();
    }

    public static List<ProvinceDTO> toProvincesDTO(List<Province> provinces){
        return provinces.stream()
                .map(x-> toProvinceDTO(x))
                .collect(Collectors.toList());
    }

    public static ProvinceDTO toProvinceDTO(Province province){
        return ProvinceDTO.builder()
                .id(province.getId())
                .region(province.getRegion())
                .name(province.getName())
                .build();
    }
    
    public static List<MunicipalityDTO> toMunicipalitiesDTO(List<Municipality> municipalities){
        return municipalities.stream()
                .map(x-> toMunicipalityDTO(x))
                .collect(Collectors.toList());
    }

    public static MunicipalityDTO toMunicipalityDTO(Municipality municipality){
        return MunicipalityDTO.builder()
                .id(municipality.getId())
                .province(municipality.getProvince())
                .name(municipality.getName())
                .build();
    }

    public static List<CountryDTO> toCountriesDTO(List<Country> countries){
        return countries.stream()
                .map(x-> toCountryDTO(x))
                .collect(Collectors.toList());
    }

    public static CountryDTO toCountryDTO(Country country){
        return CountryDTO.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }
}