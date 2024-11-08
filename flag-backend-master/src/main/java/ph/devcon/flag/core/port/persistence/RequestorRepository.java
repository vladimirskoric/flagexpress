package ph.devcon.flag.core.port.persistence;

import ph.devcon.flag.core.component.requestor.domain.Requestor;

public interface RequestorRepository {

    Requestor findById(int id);

    Requestor findByMobile(String mobile);

}