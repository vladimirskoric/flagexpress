package ph.devcon.flag.core.component.utils.application;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ph.devcon.flag.core.component.beneficiary.application.BeneficiarySectorDTO;
import ph.devcon.flag.core.component.donationrequest.application.RequestTypeDTO;
import ph.devcon.flag.core.component.utils.helper.Mappers.UtilityMapper;
import ph.devcon.flag.core.port.persistence.*;

@ApplicationScoped
public class DefaultStaticValueService implements StaticValueService {

   @Inject
   CurrencyRepository currencyRepo;

   @Inject
   DonationTypeRepository donationTypeRepo;

   @Inject
   DonorSectorRepository sectorRepo;

   @Inject
   UnitRepository unitRepo;

   @Inject
   ServiceTypeRepository svcRepo;

   @Inject
   RequestTypeRepository requestTypeRepo;

   @Inject
   BeneficiarySectorRepository beneficiarySectorRepo;

   @Inject
   ItemRepository itemRepo;

   @Inject
   RegionRepository regionRepo;

   @Inject
   ProvinceRepository provinceRepo;

   @Inject
   CountryRepository countryRepo;

   @Inject
   MunicipalityRepository municipalityRepo;

   @Override
   public List<CurrencyDTO> getCurrencies() {
      return UtilityMapper.toCurrenciesDTO(currencyRepo.findAllCurrencies());
   }

   @Override
   public List<DonationTypeDTO> getDonationTypes() {
      return UtilityMapper.toDonationTypesDTO(donationTypeRepo.findAllDonationTypes());
   }

   @Override
   public List<SectorTypeDTO> getDonorSectors() {
      return UtilityMapper.toSectorTypesDTO(sectorRepo.findAllDonorSectorTypes());
   }

   @Override
   public List<DonationUnitDTO> getUnits() {
      return UtilityMapper.toUnitsDTO(unitRepo.findAllUnits());
   }

   @Override
   public List<ServiceTypeDTO> getServiceTypes() {
      return UtilityMapper.toServiceTypesDTO(svcRepo.findAllServiceTypes());
   }

   @Override
   public List<BeneficiarySectorDTO> getBeneficiarySectors() {
      return UtilityMapper.toBeneficiarySectorsDTO(beneficiarySectorRepo.findAllSectors());
   }

   @Override
   public List<RequestTypeDTO> getDonationRequestTypes() {
      return UtilityMapper.toRequestTypesDTO(requestTypeRepo.findAllRequestTypes());
   }

   @Override
   public List<ItemDTO> getAllDonationItems() {
      return UtilityMapper.toItemsDTO(itemRepo.findAllItems());
   }

   @Override
   public List<RegionDTO> getAllRegions() {
      return UtilityMapper.toRegionsDTO(regionRepo.findAllRegions());
   }

   @Override
   public List<ProvinceDTO> getAllProvinces() {
      return UtilityMapper.toProvincesDTO(provinceRepo.findAllProvinces());
   }

   @Override
   public List<CountryDTO> getAllCountries() {
      return UtilityMapper.toCountriesDTO(countryRepo.findAllCountries());
   }

   @Override
   public List<ProvinceDTO> getProvincesByRegion(String region) {
      return UtilityMapper.toProvincesDTO(provinceRepo.findByRegion(region));
   }

   @Override
   public List<MunicipalityDTO> getAllMunicipalities() {
      return UtilityMapper.toMunicipalitiesDTO(municipalityRepo.findAllMunicipalities());
   }

   @Override
   public List<MunicipalityDTO> getMunicipalitiesByProvince(String province) {
      return UtilityMapper.toMunicipalitiesDTO(municipalityRepo.findByProvince(province));
   }
  
    
}