package ph.devcon.flag.core.component.auth.domain;


/**
 * The Exception thrown when the IAM provider cannot be accessed normally.
 */
public final class IamProviderException extends RuntimeException {

    /**
     * This exception requires a message.
     *
     * @param msg The exception message.
     */
    public IamProviderException(final String msg) {
        super(msg);
    }
}
