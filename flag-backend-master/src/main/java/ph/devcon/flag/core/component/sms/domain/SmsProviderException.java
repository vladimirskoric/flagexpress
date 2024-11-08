package ph.devcon.flag.core.component.sms.domain;


/**
 * The Exception thrown when the SMS provider cannot be accessed normally.
 */
public final class SmsProviderException extends RuntimeException {

    /**
     * This exception requires a message.
     *
     * @param msg The exception message.
     */
    public SmsProviderException(final String msg) {
        super(msg);
    }
}
