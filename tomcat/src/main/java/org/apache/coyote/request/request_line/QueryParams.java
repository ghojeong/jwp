package org.apache.coyote.request.request_line;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class QueryParams {
    private static final String QUERY_DELIMITER = "&";
    private static final String KEY_VALUE_DELIMITER = "=";

    private final Map<String, String> queryMap;

    private QueryParams() {
        this.queryMap = Collections.emptyMap();
    }

    private QueryParams(Map<String, String> queryMap) {
        this.queryMap = queryMap;
    }

    public static QueryParams from(String queryParams) {
        final Map<String, String> queryMap = Arrays
                .stream(queryParams.split(QUERY_DELIMITER))
                .map(queryParam -> queryParam.split(KEY_VALUE_DELIMITER))
                .collect(Collectors.toMap(
                        queryParam -> queryParam[0].strip(),
                        queryParam -> queryParam[1].strip()
                ));
        return new QueryParams(queryMap);
    }

    public static QueryParams emptyInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(queryMap.get(key));
    }

    @Override
    public String toString() {
        return queryMap.entrySet().stream().map(
                entry -> entry.getKey() + KEY_VALUE_DELIMITER + entry.getValue()
        ).collect(Collectors.joining(QUERY_DELIMITER));
    }

    private static class SingletonHolder {
        private static final QueryParams INSTANCE = new QueryParams();
    }
}
