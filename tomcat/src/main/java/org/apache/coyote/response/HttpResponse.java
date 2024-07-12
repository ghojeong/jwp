package org.apache.coyote.response;

import org.apache.coyote.common.HttpBody;
import org.apache.coyote.common.HttpHeaders;

public class HttpResponse {
    private final StatusLine line;
    private final HttpHeaders headers;
    private final HttpBody body;

    public HttpResponse(HttpBody body, HttpHeaders headers, StatusLine line) {
        this.line = line;
        this.headers = headers;
        this.body = body;
    }

    public HttpResponse(HttpBody body, HttpHeaders headers) {
        this(body, headers, StatusLine.ok());
    }

    public HttpResponse(HttpBody body) {
        this(body, HttpHeaders.of(body));
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
