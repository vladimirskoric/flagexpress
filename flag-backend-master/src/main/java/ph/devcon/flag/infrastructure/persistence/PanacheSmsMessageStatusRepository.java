package ph.devcon.flag.infrastructure.persistence;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import ph.devcon.flag.core.component.sms.domain.SmsMessageStatus;
import ph.devcon.flag.core.port.persistence.SmsMessageStatusRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PanacheSmsMessageStatusRepository implements SmsMessageStatusRepository, PanacheRepository<SmsMessageStatus> {

    @Override
    @Transactional
    public SmsMessageStatus saveSmsMessageStatus(final SmsMessageStatus smsMessageStatus) {
        persistAndFlush(smsMessageStatus);
        return smsMessageStatus;
    }

    @Override
    public Optional<SmsMessageStatus> findByFlagMessageStatusId(final String flagMessageStatusId) {
        return find("flag_message_status_id", flagMessageStatusId).singleResultOptional();
    }

    @Override
    public List<SmsMessageStatus> findByStatusType(final String statusType) {
        return find("status_type", statusType).list();
    }
}
