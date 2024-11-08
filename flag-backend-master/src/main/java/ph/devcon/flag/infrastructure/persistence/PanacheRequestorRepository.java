package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ph.devcon.flag.core.component.requestor.domain.Requestor;
import ph.devcon.flag.core.port.persistence.RequestorRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheRequestorRepository implements RequestorRepository, PanacheRepository<Requestor> {


    @Override
    public Requestor findById(final int id) {
        return find("id", id).firstResult();
    }

    @Override
    public Requestor findByMobile(final String mobile) {
        return find("mobile_number", mobile).firstResult();
    }

}
