package org.apache.coyote.response;

import org.apache.coyote.common.HttpProtocol;

public class StatusLine {
    private final HttpProtocol protocol;
    private final HttpStatus status;

    public StatusLine(HttpProtocol protocol, HttpStatus status) {
        this.protocol = protocol;
        this.status = status;
    }

    public StatusLine(HttpStatus status) {
        this(HttpProtocol.HTTP_11, status);
    }

    public static StatusLine ok() {
        return SingletonHolder.OK;
    }

    public static StatusLine found() {
        return SingletonHolder.FOUND;
    }

    public static StatusLine notFound() {
        return SingletonHolder.NOT_FOUND;
    }

    public static StatusLine methodNotAllowed() {
        return SingletonHolder.METHOD_NOT_ALLOWED;
    }

    public static StatusLine internalServerError() {
        return SingletonHolder.INTERNAL_SERVER_ERROR;
    }

    public String getString() {
        final String SPACE = " ";
        return protocol.getString()
                + SPACE
                + status.getString();
    }

    private static class SingletonHolder {
        private static final StatusLine OK = new StatusLine(HttpStatus.OK);
        private static final StatusLine FOUND = new StatusLine(HttpStatus.FOUND);
        private static final StatusLine NOT_FOUND = new StatusLine(HttpStatus.NOT_FOUND);
        private static final StatusLine METHOD_NOT_ALLOWED = new StatusLine(HttpStatus.METHOD_NOT_ALLOWED);
        private static final StatusLine INTERNAL_SERVER_ERROR = new StatusLine(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
