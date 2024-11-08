package ph.devcon.flag.core.port.sms;

import ph.devcon.flag.core.component.sms.domain.SmsConfirmation;
import ph.devcon.flag.core.component.sms.domain.SmsProviderException;

import java.util.Set;

public interface SmsProvider {

    SmsConfirmation sendMessage(String number, String message) throws SmsProviderException;

    SmsConfirmation retrieveMessageStatus(String messageId) throws SmsProviderException;

    Set<SmsConfirmation> retrieveMessages(String startDate, String endDate) throws SmsProviderException;

}
