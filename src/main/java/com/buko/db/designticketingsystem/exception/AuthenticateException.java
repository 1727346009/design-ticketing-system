package com.buko.db.designticketingsystem.exception;

/**
 * @author Mr.徐健威
 */
public class AuthenticateException extends RuntimeException {
    public AuthenticateException(String message) {
        super(message);
    }

    public AuthenticateException(Exception error) {
        super(error);
    }
}
