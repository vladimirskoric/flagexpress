package ph.devcon.flag.core.component.donor.domain;

public final class FileStorageProviderException extends RuntimeException {

    /**
     * This exception requires a message.
     *
     * @param msg The exception message.
     */
    public FileStorageProviderException(final String msg) {
        super(msg);
    }
}
