package net.cnitr.firstfw.exception.AccountException;

public class CredentialsException extends AuthenticationException {
    public CredentialsException() {
    }

    public CredentialsException(String message) {
        super(message);
    }

    public CredentialsException(Throwable cause) {
        super(cause);
    }

    public CredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
