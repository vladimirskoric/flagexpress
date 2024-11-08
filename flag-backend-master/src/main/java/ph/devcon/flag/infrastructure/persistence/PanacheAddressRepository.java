package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import ph.devcon.flag.core.component.address.domain.Address;
import ph.devcon.flag.core.port.persistence.AddressRepository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class PanacheAddressRepository implements AddressRepository, PanacheRepository<Address> {

    @Inject
    protected EntityManager em;
    
    @Override
    public Address findById(long id) {
        return find("id", id).firstResult();
    }

    @Override
    public List<Address> findAllAddresses() {
        return listAll(Sort.by("id"));
    }

    @Override
    public List<Address> findByProvince(final String province) {
        return list("province", province, Sort.by("id"));
    }

    @Override
    public List<Address> findByCity(final String city) {
        return list("city", city, Sort.by("id"));
    }

    @Override
    public List<Address> findByBarangay(final String barangay) {
        return list("barangay", barangay, Sort.by("id"));
    }

    @Override
    public Address createAddress(final Address address) {

        if(address.getId() != 0)
            em.merge(address);
        else
            persist(address);

        flush();
        return address;
    }
}
