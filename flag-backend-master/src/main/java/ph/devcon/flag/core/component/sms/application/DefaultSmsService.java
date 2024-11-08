package ph.devcon.flag.core.component.sms.application;

import ph.devcon.flag.core.component.sms.domain.SmsConfirmation;
import ph.devcon.flag.core.component.sms.domain.SmsMessageStatus;
import ph.devcon.flag.core.component.sms.domain.SmsProviderException;
import ph.devcon.flag.core.component.sms.domain.SmsServiceException;
import ph.devcon.flag.core.port.persistence.SmsMessageStatusRepository;
import ph.devcon.flag.core.port.sms.SmsProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class DefaultSmsService implements SmsService {

    private final SmsProvider smsProvider;

    private final SmsMessageStatusRepository smsMessageStatusRepository;

    @Inject
    DefaultSmsService(final SmsProvider smsProvider, final SmsMessageStatusRepository smsMessageStatusRepository) {
        this.smsProvider = smsProvider;
        this.smsMessageStatusRepository = smsMessageStatusRepository;
    }

    @Override
    public SmsConfirmation sendMessage(final String number, final String message) throws SmsProviderException {

        // try to send
        SmsConfirmation smsConfirmation = this.smsProvider.sendMessage(number, message);

        // store request
        SmsMessageStatus smsMessageStatus = this.smsMessageStatusRepository.saveSmsMessageStatus(
            SmsMessageStatus
                    .builder()
                    .flagMessageStatusId(smsConfirmation.getProviderMessageId())// TODO generate custom flag message ID
                    .providerMessageStatusId(smsConfirmation.getProviderMessageId())
                    .statusType(smsConfirmation.getStatus())// TODO convert to custom sms status for FLAG
                    .build()
        );

        smsConfirmation.setFlagMessageId(smsMessageStatus.getFlagMessageStatusId());
        return smsConfirmation;
    }

    @Override
    @Transactional
    public SmsConfirmation retrieveMessageStatus(final String flagMessageId) throws SmsProviderException, SmsServiceException {
        Optional<SmsMessageStatus> optionalSmsMessageStatus = this.smsMessageStatusRepository.findByFlagMessageStatusId(flagMessageId);

        if (optionalSmsMessageStatus.isEmpty()) {
            throw new SmsServiceException("Message does not exist.");
        }

        SmsMessageStatus smsMessageStatus = optionalSmsMessageStatus.get();

        if (this.STATUS_PENDING.equals(smsMessageStatus.getStatusType())) {
            // if still pending, then query to update the status
            SmsConfirmation smsConfirmation = this.smsProvider.retrieveMessageStatus(smsMessageStatus.getProviderMessageStatusId());
            smsMessageStatus.setStatusType(smsConfirmation.getStatus()); // TODO convert to custom sms status for FLAG
            smsMessageStatus = this.smsMessageStatusRepository.saveSmsMessageStatus(smsMessageStatus);
        } // otherwise, the message was already sent/failed, so just retrieve "cached" status

        return SmsConfirmation
                .builder()
                .flagMessageId(smsMessageStatus.getFlagMessageStatusId())
                .status(smsMessageStatus.getStatusType())
                .build();
    }

    @Override
    public Set<SmsConfirmation> retrieveMessages(final String startDate, final String endDate) throws SmsProviderException {
        // TODO fill-in flag message IDs
        // TODO convert to custom sms status for FLAG
        return this.smsProvider.retrieveMessages(startDate, endDate);
    }
}
