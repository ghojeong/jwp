package org.apache.coyote.exception;

import org.apache.coyote.request.HttpRequest;

public class UnsupportedHandlerException extends UnsupportedException {
    private static final String MESSAGE = "Unsupported Handler: ";

    public UnsupportedHandlerException(HttpRequest request) {
        super(MESSAGE + request);
    }
}
