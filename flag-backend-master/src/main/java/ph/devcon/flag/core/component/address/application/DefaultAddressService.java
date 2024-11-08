package ph.devcon.flag.core.component.address.application;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import ph.devcon.flag.core.component.address.domain.Address;
import ph.devcon.flag.core.port.persistence.AddressRepository;

@ApplicationScoped
public class DefaultAddressService implements AddressService {

    @Inject
    AddressRepository addressRepository;

    @Override
    public Address findById(long id) {
      return addressRepository.findById(id);
    }

    @Override
    public List<Address> findAllAddresses() {
        return addressRepository.findAllAddresses();
    }

    @Override
    public List<Address> findByProvince(String province) {
        return addressRepository.findByProvince(province);
    }

    @Override
    public List<Address> findByCity(String city) {
        return addressRepository.findByCity(city);
    }

    @Override
    public List<Address> findByBarangay(String barangay) {
        return addressRepository.findByBarangay(barangay);
    }

    @Transactional
    @Override
    public Address createUpdateAddress(Address address) {
        return addressRepository.createAddress(address);
    } 
}
