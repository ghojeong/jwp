package org.apache.coyote.request.request_line;

import java.util.Optional;

public class RequestTarget {
    private final String path;
    private final QueryParams queryParams;

    private RequestTarget(String path, QueryParams queryParams) {
        this.path = path;
        this.queryParams = queryParams;
    }

    private RequestTarget(String path) {
        this(path, QueryParams.emptyInstance());
    }

    public static RequestTarget from(String uri) {
        final String DELIMITER = "\\?";
        String[] parts = uri.split(DELIMITER);
        return parts.length != 2
                ? new RequestTarget(uri)
                : new RequestTarget(parts[0], QueryParams.from(parts[1]));
    }

    public String path() {
        return path;
    }

    public Optional<String> queryParam(String key) {
        return queryParams.get(key);
    }

    @Override
    public String toString() {
        final String DELIMITER = "?";
        return path + DELIMITER + queryParams;
    }
}
