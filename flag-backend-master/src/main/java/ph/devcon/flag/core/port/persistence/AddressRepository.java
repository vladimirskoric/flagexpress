package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.address.domain.Address;

public interface AddressRepository {
    Address findById(long id);
    List<Address> findAllAddresses();
    List<Address> findByProvince(String province);
    List<Address> findByCity(String city);
    List<Address> findByBarangay(String barangay);
    Address createAddress(Address address);
}
