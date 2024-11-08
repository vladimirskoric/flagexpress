package ph.devcon.flag.core.component.sms.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ph.devcon.flag.core.component.sms.domain.SmsConfirmation;
import ph.devcon.flag.core.component.sms.domain.SmsMessageStatus;
import ph.devcon.flag.core.component.sms.domain.SmsProviderException;
import ph.devcon.flag.core.port.persistence.SmsMessageStatusRepository;
import ph.devcon.flag.core.port.sms.SmsProvider;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DefaultSmsServiceTest {

    SmsProvider mockSmsProvider;
    SmsMessageStatusRepository mockSmsMessageStatusRepository;
    SmsService smsService;

    @BeforeEach
    void before() {
        this.mockSmsProvider = Mockito.mock(SmsProvider.class);
        this.mockSmsMessageStatusRepository = Mockito.mock(SmsMessageStatusRepository.class);
        this.smsService = new DefaultSmsService(this.mockSmsProvider, this.mockSmsMessageStatusRepository);
    }

    @Test
    void testSendingSuccess() {

        String number = "test.mobile_number";
        String message = "test.message";
        String status = "test.status.sent";
        SmsConfirmation validSmsConfirmation = SmsConfirmation.builder().providerMessageId(number).status(status).build();
        when(this.mockSmsProvider.sendMessage(any(), any())).thenReturn(validSmsConfirmation);

        SmsMessageStatus validSmsMessageStatus = SmsMessageStatus
                .builder()
                .flagMessageStatusId(validSmsConfirmation.getProviderMessageId())
                .providerMessageStatusId(validSmsConfirmation.getProviderMessageId())
                .statusType(validSmsConfirmation.getStatus())
                .build();
        when(this.mockSmsMessageStatusRepository.saveSmsMessageStatus(any())).thenReturn(validSmsMessageStatus);

        try {
            SmsConfirmation smsConfirmation = this.smsService.sendMessage(number, message);
            assertNotNull(smsConfirmation);
            assertEquals(number, smsConfirmation.getProviderMessageId());
            assertEquals(status, smsConfirmation.getStatus());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void testSendingError() {
        String exceptionMessage = "test.exception";
        when(this.mockSmsProvider.sendMessage(any(), any())).thenThrow(new SmsProviderException(exceptionMessage));

        try {
            this.smsService.sendMessage("number", "message");
        } catch (SmsProviderException e) {
            assertEquals(exceptionMessage, e.getMessage());
            return;
        }

        Assertions.fail("should have thrown an exception");
    }

    @Test
    void testRetrievingMessageStatusSuccess() {

        String messageId = "test.mesage_id";
        String status = SmsService.STATUS_SENT;
        SmsConfirmation validSmsConfirmation = SmsConfirmation.builder()
                        .providerMessageId(messageId)
                        .flagMessageId(messageId)
                        .status(status)
                        .build();
        when(this.mockSmsProvider.retrieveMessageStatus(any())).thenReturn(validSmsConfirmation);

        SmsMessageStatus validSmsMessageStatus = SmsMessageStatus
                .builder()
                .flagMessageStatusId(validSmsConfirmation.getProviderMessageId())
                .providerMessageStatusId(validSmsConfirmation.getProviderMessageId())
                .statusType(validSmsConfirmation.getStatus())
                .build();
        when(this.mockSmsMessageStatusRepository.findByFlagMessageStatusId(any())).thenReturn(Optional.ofNullable(validSmsMessageStatus));

        try {
            SmsConfirmation smsConfirmation = this.smsService.retrieveMessageStatus(messageId);
            assertNotNull(smsConfirmation);
            assertEquals(messageId, smsConfirmation.getFlagMessageId());
            assertEquals(status, smsConfirmation.getStatus());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void testRetrievingMessageStatusError() {
        String exceptionMessage = "test.exception";
        when(this.mockSmsProvider.retrieveMessageStatus(any())).thenThrow(new SmsProviderException(exceptionMessage));

        SmsMessageStatus validSmsMessageStatus = SmsMessageStatus
                .builder()
                .flagMessageStatusId("")
                .providerMessageStatusId("")
                .statusType(SmsService.STATUS_PENDING)
                .build();
        when(this.mockSmsMessageStatusRepository.findByFlagMessageStatusId(any())).thenReturn(Optional.ofNullable(validSmsMessageStatus));

        try {
            this.smsService.retrieveMessageStatus("00");
        } catch (SmsProviderException e) {
            assertEquals(exceptionMessage, e.getMessage());
            return;
        }

        Assertions.fail("should have thrown an exception");
    }

}
