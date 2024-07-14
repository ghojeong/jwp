package org.apache.coyote.request.request_line;

import java.util.Collections;
import java.util.Map;

public class HttpPath {
    private static final String DELIMITER = "/";

    private final String path;
    private final Map<String, String> variableMap;

    private HttpPath(String path, Map<String, String> variableMap) {
        this.path = path;
        this.variableMap = variableMap;
    }

    public static HttpPath from(String path) {
        return new HttpPath(path, Collections.emptyMap());
    }

    public String path() {
        return path;
    }

    public String getString() {
        return variableMap.isEmpty()
                ? path
                : path + DELIMITER + String.join(
                DELIMITER, variableMap.values()
        );
    }
}
