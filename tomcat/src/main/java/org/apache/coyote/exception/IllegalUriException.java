package org.apache.coyote.exception;

public class IllegalUriException extends IllegalArgumentException {
    private static final String MESSAGE = "Illegal URI: %s";

    public IllegalUriException(String uri) {
        super(MESSAGE.formatted(uri));
    }
}
