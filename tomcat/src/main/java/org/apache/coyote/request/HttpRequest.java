package org.apache.coyote.request;

import org.apache.coyote.common.ContentType;
import org.apache.coyote.common.HttpBody;
import org.apache.coyote.common.HttpHeaders;
import org.apache.coyote.common.HttpProtocol;
import org.apache.coyote.request.request_line.HttpMethod;
import org.apache.coyote.request.request_line.RequestLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

public class HttpRequest {
    private final RequestLine line;
    private final HttpHeaders headers;
    private final HttpBody body;

    private HttpRequest(RequestLine line, HttpHeaders headers, HttpBody body) {
        this.line = line;
        this.headers = headers;
        this.body = body;
    }

    public static HttpRequest from(BufferedReader reader) throws IOException {
        final RequestLine line = readRequestLine(reader);
        final HttpHeaders headers = readRequestHeaders(reader);
        final HttpBody body = readRequestBody(reader, headers);
        return new HttpRequest(line, headers, body);
    }

    private static RequestLine readRequestLine(BufferedReader reader) throws IOException {
        return RequestLine.from(reader.readLine());
    }

    private static HttpHeaders readRequestHeaders(BufferedReader reader) {
        return new HttpHeaders(reader.lines().takeWhile(
                line -> !line.isEmpty()
        ).toList());
    }

    private static HttpBody readRequestBody(BufferedReader reader, HttpHeaders headers) throws IOException {
        final int contentLength = headers.contentLength();
        if (contentLength <= 0) {
            return HttpBody.emptyInstance();
        }
        final char[] body = new char[contentLength];
        reader.read(body);
        return new HttpBody(headers.contentType(), new String(body));
    }

    public boolean matchMethod(HttpMethod method) {
        return line.matchMethod(method);
    }

    public String path() {
        return line.path();
    }

    public Optional<String> queryParam(String key) {
        return line.queryParam(key);
    }

    public HttpProtocol protocol() {
        return line.protocol();
    }

    public Optional<String> header(String key) {
        return headers.get(key);
    }

    public ContentType contentType() {
        return headers.contentType();
    }

    public int contentLength() {
        return headers.contentLength();
    }

    public String body() {
        return body.toString();
    }

    @Override
    public String toString() {
        final String SEPARATOR = "\r\n";
        return line + SEPARATOR
                + headers + SEPARATOR
                + SEPARATOR + body;
    }
}
