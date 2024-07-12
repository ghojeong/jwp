package org.apache.coyote.exception;

public class UnsupportedProtocolException extends UnsupportedException {
    private static final String MESSAGE = "Unsupported HttpProtocol: %s";

    public UnsupportedProtocolException(String protocolName) {
        super(MESSAGE.formatted(protocolName));
    }
}
