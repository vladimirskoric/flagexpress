package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.donations.domain.ServiceType;

public interface ServiceTypeRepository {
    List<ServiceType> findAllServiceTypes();
    ServiceType findByCode(String code);
    ServiceType findById(int id);
}


