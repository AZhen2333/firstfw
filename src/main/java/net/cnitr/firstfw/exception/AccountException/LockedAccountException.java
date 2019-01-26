package net.cnitr.firstfw.exception.AccountException;

import net.cnitr.firstfw.exception.ShiroException;

public class LockedAccountException extends ShiroException{
    public LockedAccountException() {
    }

    public LockedAccountException(String message) {
        super(message);
    }

    public LockedAccountException(Throwable cause) {
        super(cause);
    }

    public LockedAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
