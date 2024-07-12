package org.apache.coyote.request.request_line;

import org.apache.coyote.common.HttpProtocol;

import java.util.Optional;

public class RequestLine {
    private final HttpMethod method;
    private final RequestTarget target;
    private final HttpProtocol protocol;

    private RequestLine(HttpMethod method, RequestTarget target, HttpProtocol protocol) {
        this.method = method;
        this.target = target;
        this.protocol = protocol;
    }

    public static RequestLine from(String requestLine) {
        final String DELIMITER = " ";
        final String[] parts = requestLine.split(DELIMITER);
        return new RequestLine(
                HttpMethod.from(parts[0]),
                RequestTarget.from(parts[1]),
                HttpProtocol.from(parts[2])
        );
    }

    public boolean matchMethod(HttpMethod method) {
        return this.method.equals(method);
    }

    public String path() {
        return target.path();
    }

    public Optional<String> queryParam(String key) {
        return target.queryParam(key);
    }

    public HttpProtocol protocol() {
        return protocol;
    }

    @Override
    public String toString() {
        final String SPACE = " ";
        return method.name() + SPACE + target + SPACE + protocol;
    }
}
