package ph.devcon.flag.infrastructure.sms.semaphore;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import ph.devcon.flag.core.component.sms.domain.SmsConfirmation;
import ph.devcon.flag.core.component.sms.domain.SmsProviderException;
import ph.devcon.flag.core.port.sms.SmsProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Form;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
@Slf4j
public class SemaphoreSmsProvider implements SmsProvider {

    private static final String KEY_APIKEY = "apikey";
    private static final String KEY_SENDERNAME = "sendername";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_STARTDATE = "startDate";
    private static final String KEY_ENDDATE = "endDate";
    private static final String KEY_LIMIT = "limit";

    @Inject
    @RestClient
    SemaphoreApi semaphoreApi;

    @ConfigProperty(name = "semaphore.api_key_env_var")
    String apiKeyEnvVar;

    @ConfigProperty(name = "semaphore.sender_name")
    String senderName;

    @ConfigProperty(name = "semaphore.limit")
    String limit;

    /**
     * @param number Your recipient’s mobile number
     * @param message What you want to tell your recipient
     * @return SmsConfirmation
     * @throws SmsProviderException For network connection or server side errors
     */
    @Override
    public SmsConfirmation sendMessage(final String number, final String message) throws SmsProviderException {
        Form form = new Form();
        form.param(KEY_APIKEY, System.getenv(this.apiKeyEnvVar));
        form.param(KEY_SENDERNAME, this.senderName);
        form.param(KEY_NUMBER, number);
        form.param(KEY_MESSAGE, message);

        try {
            SemaphoreMessageResponse[] responseArray = this.semaphoreApi.sendMessage(form);
            return SmsConfirmation
                    .builder()
                    .providerMessageId(responseArray[0].getMessageId())
                    .status(responseArray[0].getStatus())
                    .build();
        } catch (final SmsProviderException e) {
            // usually, authentication issue
            log.error(e.getMessage());
            throw e;
        }  catch (final RuntimeException e) {
            // usually, connection issue
            log.error(e.getMessage());
            throw new SmsProviderException("SMS API not accessible");
        }
    }

    @Override
    public SmsConfirmation retrieveMessageStatus(final String messageId) {
        try {
            SemaphoreMessageResponse[] responseArray = this.semaphoreApi.retrieveMessage(messageId, System.getenv(this.apiKeyEnvVar));
            return SmsConfirmation
                    .builder()
                    .providerMessageId(responseArray[0].getMessageId())
                    .status(responseArray[0].getStatus())
                    .build();
        } catch (final SmsProviderException e) {
            log.error(e.getMessage());
            throw e;
        }  catch (final RuntimeException e) {
            log.error(e.getMessage());
            throw new SmsProviderException("SMS API not accessible");
        }
    }

    @Override
    public Set<SmsConfirmation> retrieveMessages(final String startDate, final String endDate) {
        Form form = new Form();
        form.param(KEY_APIKEY, System.getenv(this.apiKeyEnvVar));
        form.param(KEY_STARTDATE, startDate);
        form.param(KEY_ENDDATE, endDate);
        form.param(KEY_LIMIT, this.limit);

        try {
            SemaphoreMessageResponse[] responseArray = this.semaphoreApi.retrieveMessages(form);
            Stream<SemaphoreMessageResponse> stream = Arrays.stream(responseArray);
            return stream.map(messageResponse ->
                SmsConfirmation
                        .builder()
                        .providerMessageId(messageResponse.getRecipient())
                        .status(messageResponse.getStatus())
                        .build()
            ).collect(Collectors.toUnmodifiableSet());
        } catch (final SmsProviderException e) {
            log.error(e.getMessage());
            throw e;
        }  catch (final RuntimeException e) {
            log.error(e.getMessage());
            throw new SmsProviderException("SMS API not accessible");
        }
    }
}
