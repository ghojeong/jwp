package org.apache.coyote.exception;

import org.apache.coyote.request.HttpRequest;

public class MethodNotAllowedException extends RuntimeException {
    private static final String MESSAGE = "Method Not Allowed: %s";

    public MethodNotAllowedException(HttpRequest request) {
        super(String.format(MESSAGE, request.requestLine()));
    }
}
