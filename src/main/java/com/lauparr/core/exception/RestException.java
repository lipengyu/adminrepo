package com.lauparr.core.exception;

/**
 * Created by lauparr on 22/11/2016.
 */
public class RestException extends Exception {

    public RestException() {
        super();
    }

    public RestException(String message, Object... properties) {
        super(String.format(message, properties));
    }

    public RestException(Throwable cause) {
        super(cause);
    }
}
