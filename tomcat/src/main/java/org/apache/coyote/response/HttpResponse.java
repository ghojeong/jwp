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

    public HttpResponse(HttpBody body, StatusLine line) {
        this(body, HttpHeaders.of(body), line);
    }

    public HttpResponse(HttpBody body, HttpHeaders headers) {
        this(body, headers, StatusLine.ok());
    }

    public HttpResponse(HttpBody body) {
        this(body, HttpHeaders.of(body));
    }

    public HttpResponse(StatusLine line) {
        this.line = line;
        this.headers = HttpHeaders.of();
        this.body = HttpBody.emptyInstance();
    }

    public static HttpResponse redirect(String location) {
        return new HttpResponse(
                HttpBody.emptyInstance(),
                HttpHeaders.redirect(location),
                StatusLine.found()
        );
    }

    public byte[] getBytes() {
        return getString().getBytes();
    }

    public String getString() {
        final String SEPARATOR = "\r\n";
        return line.getString() + SEPARATOR
                + headers.getString() + SEPARATOR
                + SEPARATOR + body.getString();
    }

    public HttpResponse setSession(String sessionId) {
        headers.setSession(sessionId);
        return this;
    }
}
