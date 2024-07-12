package org.apache.coyote.common;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HttpHeaders {
    private static final String DELIMITER = ": ";

    private final Map<String, String> headerMap;

    public HttpHeaders(List<String> headers) {
        this.headerMap = headers.stream()
                .map(header -> header.split(DELIMITER))
                .collect(Collectors.toMap(
                        header -> header[0].strip(),
                        header -> header[1].strip()
                ));
    }

    public static HttpHeaders of(String... headers) {
        return new HttpHeaders(Arrays.asList(headers));
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(headerMap.get(key));
    }

    public int contentLength() {
        final String KEY = "Content-Length";
        final String DEFAULT_CONTENT_LENGTH = "0";
        return Integer.parseInt(
                get(KEY).orElse(DEFAULT_CONTENT_LENGTH)
        );
    }

    @Override
    public String toString() {
        final String SEPARATOR = "\r\n";
        return headerMap.entrySet().stream().map(
                entry -> entry.getKey() + DELIMITER + entry.getValue()
        ).collect(Collectors.joining(SEPARATOR));
    }
}
