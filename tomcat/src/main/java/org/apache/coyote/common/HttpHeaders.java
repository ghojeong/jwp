package org.apache.coyote.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HttpHeaders {
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String DELIMITER = ": ";

    private final Map<String, String> headerMap = new HashMap<>();

    public HttpHeaders(List<String> headers) {
        headers.stream().map(header -> header.split(DELIMITER))
                .forEach(header -> headerMap.put(header[0].strip(), header[1].strip()));
    }

    public static HttpHeaders of(String... headers) {
        return new HttpHeaders(Arrays.asList(headers));
    }

    public static HttpHeaders of(HttpBody body, String... headers) {
        return of(headers).setContentInfo(body);
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(headerMap.get(key));
    }

    public ContentType contentType() {
        final String DEFAULT_CONTENT_TYPE = "text/plain";
        return ContentType.from(
                get(CONTENT_TYPE).orElse(DEFAULT_CONTENT_TYPE)
        );
    }

    public int contentLength() {
        final String DEFAULT_CONTENT_LENGTH = "0";
        return Integer.parseInt(
                get(CONTENT_LENGTH).orElse(DEFAULT_CONTENT_LENGTH)
        );
    }

    private HttpHeaders setContentInfo(HttpBody body) {
        headerMap.put(CONTENT_LENGTH, body.contentLength());
        headerMap.put(CONTENT_TYPE, body.contentType());
        return this;
    }

    public String getString() {
        final String SEPARATOR = "\r\n";
        return headerMap.entrySet().stream().map(
                entry -> entry.getKey() + DELIMITER + entry.getValue()
        ).collect(Collectors.joining(SEPARATOR));
    }
}
