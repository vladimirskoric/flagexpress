package ph.devcon.flag.infrastructure.sms.semaphore;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;
import ph.devcon.flag.core.component.sms.domain.SmsProviderException;

import javax.ws.rs.core.Response;

public final class SemaphoreResponseExceptionMapper implements ResponseExceptionMapper<RuntimeException> {

    public static final int CODE_BAD_RESPONSE = 400;
    public static final int CODE_INTERNAL_ERROR = 500;

    @Override
    public RuntimeException toThrowable(final Response response) {
        final int status = response.getStatus();

        if (CODE_INTERNAL_ERROR < status) {
            return new SmsProviderException("An SMS provider error occurred.");
        } else if (CODE_BAD_RESPONSE < status) {
            return new SmsProviderException("Something is wrong with the request.");
        }

        return new SmsProviderException("An unknown error occurred. Please contact the development team.");
    }

}
