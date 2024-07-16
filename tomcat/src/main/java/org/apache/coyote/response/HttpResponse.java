package org.apache.coyote.response;

import org.apache.coyote.common.HttpBody;
import org.apache.coyote.common.HttpHeaders;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

    public void transferTo(OutputStream outputStream) throws IOException {
        final InputStream inputStream = new ByteArrayInputStream(
                getBytes()
        );
        inputStream.transferTo(outputStream);
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
}
