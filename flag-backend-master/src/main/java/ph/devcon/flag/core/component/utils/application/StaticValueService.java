package ph.devcon.flag.core.component.utils.application;

import ph.devcon.flag.core.component.beneficiary.application.BeneficiarySectorDTO;
import ph.devcon.flag.core.component.donationrequest.application.RequestTypeDTO;

import java.util.List;

public interface StaticValueService {
    List<CurrencyDTO> getCurrencies();
    List<DonationTypeDTO> getDonationTypes();
    List<SectorTypeDTO> getDonorSectors();
    List<DonationUnitDTO> getUnits();
    List<ServiceTypeDTO> getServiceTypes();
    List<BeneficiarySectorDTO> getBeneficiarySectors();
    List<RequestTypeDTO> getDonationRequestTypes();
    List<ItemDTO> getAllDonationItems();
    List<RegionDTO> getAllRegions();
    List<ProvinceDTO> getAllProvinces();
    List<ProvinceDTO> getProvincesByRegion(String region);
    List<MunicipalityDTO> getAllMunicipalities();
    List<MunicipalityDTO> getMunicipalitiesByProvince(String province);
    List<CountryDTO> getAllCountries();
}
