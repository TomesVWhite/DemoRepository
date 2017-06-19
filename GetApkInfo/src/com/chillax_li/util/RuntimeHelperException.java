package com.chillax_li.util;

/**
 * Created by rover12421 on 10/27/15.
 */
public class RuntimeHelperException extends Exception {
    public RuntimeHelperException(Throwable cause) {
        super(cause);
    }

    public RuntimeHelperException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeHelperException(String message) {
        super(message);
    }

    public RuntimeHelperException() {
    }
}
