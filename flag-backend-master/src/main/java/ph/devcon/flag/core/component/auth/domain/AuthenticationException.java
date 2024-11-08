package ph.devcon.flag.core.component.auth.domain;


/**
 * This gets thrown when the username/password does not match the records in the IAM provider.
 */
public final class AuthenticationException extends RuntimeException {

    /**
     * This exception requires a message.
     *
     * @param msg the exception message.
     */
    public AuthenticationException(final String msg) {
        super(msg);
    }

}
