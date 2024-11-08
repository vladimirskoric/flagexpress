
package ph.devcon.flag.core.component.address.application;

import java.util.List;

import ph.devcon.flag.core.component.address.domain.Address;

public interface AddressService {
    public Address findById(long id);
    public List<Address> findAllAddresses();
    public List<Address> findByProvince(final String province);
    public List<Address> findByCity(final String city);
    public List<Address> findByBarangay(final String barangay);
    public Address createUpdateAddress(final Address address);
}
