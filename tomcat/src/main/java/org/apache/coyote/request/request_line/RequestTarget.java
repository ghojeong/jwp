package org.apache.coyote.request.request_line;

import java.util.Optional;

public class RequestTarget {
    private final HttpPath httpPath;
    private final QueryParams queryParams;

    private RequestTarget(HttpPath httpPath, QueryParams queryParams) {
        this.httpPath = httpPath;
        this.queryParams = queryParams;
    }

    private RequestTarget(String path) {
        this(HttpPath.from(path), QueryParams.emptyInstance());
    }

    public static RequestTarget from(String uri) {
        final String DELIMITER = "\\?";
        String[] parts = uri.split(DELIMITER);
        return parts.length != 2
                ? new RequestTarget(uri)
                : new RequestTarget(
                HttpPath.from(parts[0]),
                QueryParams.from(parts[1])
        );
    }

    public String path() {
        return httpPath.path();
    }

    public Optional<String> queryParam(String key) {
        return queryParams.get(key);
    }

    public String getString() {
        final String DELIMITER = "?";
        return httpPath.getString() + DELIMITER
                + queryParams.getString();
    }
}
