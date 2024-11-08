package ph.devcon.flag.core.component.sms.application;

import ph.devcon.flag.core.component.sms.domain.SmsConfirmation;
import ph.devcon.flag.core.component.sms.domain.SmsProviderException;
import ph.devcon.flag.core.component.sms.domain.SmsServiceException;

import java.util.Set;

public interface SmsService {

    String STATUS_PENDING = "Pending";
    String STATUS_SENT = "Sent";
    String STATUS_FAILED = "Failed";

    SmsConfirmation sendMessage(String number, String message) throws SmsProviderException, SmsServiceException;

    SmsConfirmation retrieveMessageStatus(String messageId) throws SmsProviderException;

    Set<SmsConfirmation> retrieveMessages(String startDate, String endDate) throws SmsProviderException;

}
