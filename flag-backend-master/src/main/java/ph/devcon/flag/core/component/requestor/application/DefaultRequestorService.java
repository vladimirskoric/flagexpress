package ph.devcon.flag.core.component.requestor.application;

import ph.devcon.flag.core.component.requestor.domain.Requestor;
import ph.devcon.flag.core.port.persistence.RequestorRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DefaultRequestorService implements RequestorService {

    @Inject
    RequestorRepository requestorRepository;

    @Override
    public Requestor getRequestor(final int id) {
        return requestorRepository.findById(id);
    }

}
