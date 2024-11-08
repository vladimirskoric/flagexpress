package ph.devcon.flag.infrastructure.sms.semaphore;

import org.junit.Rule;
import org.junit.contrib.java.lang.system.EnvironmentVariables;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ph.devcon.flag.core.component.sms.domain.SmsConfirmation;
import ph.devcon.flag.core.component.sms.domain.SmsProviderException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SemaphoreSmsProviderTest {

    @Rule
    private final EnvironmentVariables environmentVariables = new EnvironmentVariables();

    private SemaphoreApi mockSemaphoreApi;
    private SemaphoreSmsProvider smsProvider;

    @BeforeEach
    void before() {
        this.mockSemaphoreApi = mock(SemaphoreApi.class);
        this.smsProvider = new SemaphoreSmsProvider();
        this.smsProvider.apiKeyEnvVar = "var";
        environmentVariables.set(this.smsProvider.apiKeyEnvVar, "value");
    }

    @Test
    void testSendingSuccess() {
        // setup
        String messageId = "test.message_id";
        String message = "test.message";
        String status = "test.status.sent";
        SemaphoreMessageResponse[] validResponse = new SemaphoreMessageResponse[] {
                SemaphoreMessageResponse.builder()
                .messageId(messageId)
                .status(status)
                .build()
        };
        when(this.mockSemaphoreApi.sendMessage(any())).thenReturn(validResponse);

        // actual call
        this.smsProvider.semaphoreApi = this.mockSemaphoreApi;
        SmsConfirmation smsConfirmation = this.smsProvider.sendMessage(messageId, message);

        // assertions
        assertNotNull(smsConfirmation);
        assertEquals(messageId, smsConfirmation.getProviderMessageId());
        assertEquals(status, smsConfirmation.getStatus());
    }

    @Test
    void testSendingError() {
        // setup
        when(this.mockSemaphoreApi.sendMessage(any())).thenThrow(new RuntimeException());

        // actual call
        this.smsProvider.semaphoreApi = this.mockSemaphoreApi;
        try {
            this.smsProvider.sendMessage("number", "message");
        } catch (SmsProviderException e) {
            assertNotNull(e);
            return;
        }

        Assertions.fail("should have thrown an exception");
    }

    @Test
    void testRetrievingMessageStatusSuccess() {
        // setup
        String messageId = "test.message_id";
        String message = "test.message";
        String status = "test.status.sent";
        SemaphoreMessageResponse[] validResponse = new SemaphoreMessageResponse[] {
                SemaphoreMessageResponse.builder()
                        .messageId(messageId)
                        .message(message)
                        .status(status)
                        .build()
        };
        when(this.mockSemaphoreApi.retrieveMessage(any(), any())).thenReturn(validResponse);

        // actual call
        this.smsProvider.semaphoreApi = this.mockSemaphoreApi;
        SmsConfirmation smsConfirmation = this.smsProvider.retrieveMessageStatus("12345678");

        // assertions
        assertNotNull(smsConfirmation);
        assertEquals(messageId, smsConfirmation.getProviderMessageId());
        assertEquals(status, smsConfirmation.getStatus());
    }

    @Test
    void testRetrievingMessageStatusError() {
        // setup
        when(this.mockSemaphoreApi.retrieveMessage(any(), any())).thenThrow(new RuntimeException());

        // actual call
        this.smsProvider.semaphoreApi = this.mockSemaphoreApi;
        try {
            this.smsProvider.retrieveMessageStatus("12345678");
        } catch (SmsProviderException e) {
            assertNotNull(e);
            return;
        }

        Assertions.fail("should have thrown an exception");
    }

}
