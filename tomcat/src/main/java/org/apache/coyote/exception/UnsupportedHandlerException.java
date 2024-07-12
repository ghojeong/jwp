package org.apache.coyote.exception;

public class UnsupportedHandlerException extends UnsupportedException {
    private static final String MESSAGE = "Unsupported Handler: Http Handler is not matched";

    public UnsupportedHandlerException() {
        super(MESSAGE);
    }
}
