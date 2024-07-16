package org.apache.coyote.request.request_line;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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

    public static HttpPath of(String httpPath, String[] variableNames) {
        final Map<String, String> variableMap = new HashMap<>();
        final String[] parts = httpPath.split(DELIMITER);
        final int indexOfVariable = parts.length - variableNames.length;
        final String[] pathParts = Arrays.copyOf(parts, indexOfVariable);
        final String[] variableParts = Arrays.copyOfRange(parts, indexOfVariable, parts.length);
        for (int i = 0; i < variableNames.length; i++) {
            variableMap.put(variableNames[i], variableParts[i]);
        }
        return new HttpPath(
                String.join(DELIMITER, pathParts),
                variableMap
        );
    }

    public String variable(String variableName) {
        final String DEFAULT = "";
        return variableMap.getOrDefault(variableName, DEFAULT);
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
