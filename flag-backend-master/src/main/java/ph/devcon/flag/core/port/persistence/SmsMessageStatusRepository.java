package ph.devcon.flag.core.port.persistence;

import ph.devcon.flag.core.component.sms.domain.SmsMessageStatus;

import java.util.List;
import java.util.Optional;

public interface SmsMessageStatusRepository {

    SmsMessageStatus saveSmsMessageStatus(SmsMessageStatus smsMessageStatus);

    Optional<SmsMessageStatus> findByFlagMessageStatusId(String flagMessageStatusId);

    List<SmsMessageStatus> findByStatusType(String statusType);
}
