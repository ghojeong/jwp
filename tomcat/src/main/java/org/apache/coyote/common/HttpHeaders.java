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

    private final Map<String, String> headerMap;
    private final Cookies cookies;

    private HttpHeaders(Map<String, String> headerMap) {
        final String COOKIE_KEY = "Cookie";
        this.headerMap = new HashMap<>(headerMap);
        this.cookies = Cookies.from(headerMap.get(COOKIE_KEY));
    }

    public static HttpHeaders from(List<String> headers) {
        return new HttpHeaders(headers.stream().map(
                header -> header.split(DELIMITER)
        ).collect(Collectors.toMap(
                header -> header[0].strip(),
                header -> header[1].strip()
        )));
    }

    public static HttpHeaders of(String... headers) {
        return HttpHeaders.from(Arrays.asList(headers));
    }

    public static HttpHeaders of(HttpBody body, String... headers) {
        return of(headers).setContentInfo(body);
    }

    public static HttpHeaders redirect(String location) {
        final String PREFIX = "Location: ";
        return HttpHeaders.of(PREFIX + location);
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

    public Optional<String> cookie(String key) {
        return cookies.get(key);
    }

    public Optional<String> sessionId() {
        final String KEY = "JSESSIONID";
        return cookie(KEY);
    }

    public HttpHeaders setSession(String sessionId) {
        final String KEY = "Set-Cookie";
        final String PREFIX = "JSESSIONID=";
        return setHeader(Map.of(
                KEY, PREFIX + sessionId
        ));
    }

    private HttpHeaders setContentInfo(HttpBody body) {
        return setHeader(Map.of(
                CONTENT_LENGTH, body.contentLength(),
                CONTENT_TYPE, body.contentType()
        ));
    }

    private HttpHeaders setHeader(Map<String, String> headers) {
        headerMap.putAll(headers);
        return this;
    }

    public String getString() {
        final String SEPARATOR = "\r\n";
        return headerMap.entrySet().stream().map(
                entry -> entry.getKey() + DELIMITER + entry.getValue()
        ).collect(Collectors.joining(SEPARATOR));
    }
}
