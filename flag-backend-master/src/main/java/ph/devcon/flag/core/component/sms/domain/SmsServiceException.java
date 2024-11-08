package ph.devcon.flag.core.component.sms.domain;

public final class SmsServiceException extends RuntimeException {

    /**
     * This exception requires a message.
     *
     * @param msg The exception message.
     */
    public SmsServiceException(final String msg) {
        super(msg);
    }
}
