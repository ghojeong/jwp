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

    public static StatusLine notFound() {
        return SingletonHolder.NOT_FOUND;
    }

    public static StatusLine internalServerError() {
        return SingletonHolder.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String toString() {
        final String SPACE = " ";
        return protocol + SPACE + status;
    }

    private static class SingletonHolder {
        private static final StatusLine OK = new StatusLine(HttpStatus.OK);
        private static final StatusLine NOT_FOUND = new StatusLine(HttpStatus.NOT_FOUND);
        private static final StatusLine INTERNAL_SERVER_ERROR = new StatusLine(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
