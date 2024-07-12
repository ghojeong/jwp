package org.apache.coyote.response;

import org.apache.coyote.common.HttpBody;
import org.apache.coyote.common.HttpHeaders;

public class HttpResponse {
    private final StatusLine line;
    private final HttpHeaders headers;
    private final HttpBody body;

    public HttpResponse(StatusLine line, HttpHeaders headers, HttpBody body) {
        this.line = line;
        this.headers = headers;
        this.body = body;
    }

    public HttpResponse(HttpHeaders headers, HttpBody body) {
        this(StatusLine.ok(), headers, body);
    }


    public byte[] getBytes() {
        return toString().getBytes();
    }

    @Override
    public String toString() {
        final String SEPARATOR = "\r\n";
        return line + SEPARATOR
                + headers + SEPARATOR
                + SEPARATOR + body;
    }
}
