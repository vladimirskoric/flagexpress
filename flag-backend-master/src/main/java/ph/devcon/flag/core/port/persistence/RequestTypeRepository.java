package ph.devcon.flag.core.port.persistence;

import java.util.List;

import ph.devcon.flag.core.component.donationrequest.domain.RequestType;

public interface RequestTypeRepository {
    RequestType findByName(String name);
    List<RequestType> findAllRequestTypes();
}
