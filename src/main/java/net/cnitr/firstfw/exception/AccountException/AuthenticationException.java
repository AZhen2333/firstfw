package net.cnitr.firstfw.exception.AccountException;

import net.cnitr.firstfw.exception.ShiroException;

public class AuthenticationException extends ShiroException {
    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(Throwable cause) {
        super(cause);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
