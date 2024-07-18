package org.apache.coyote.common;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyMap;

public class Cookies {
    private static final String COOKIE_DELIMITER = ";";
    private static final String KEY_VALUE_DELIMITER = "=";

    private final Map<String, String> cookies;

    private Cookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public static Cookies from(String cookies) {
        if (cookies == null || cookies.isBlank()) {
            return SingletonHolder.EMPTY_INSTANCE;
        }
        return new Cookies(Arrays.stream(
                cookies.split(COOKIE_DELIMITER)
        ).map(
                String::trim
        ).filter(
                cookie -> cookie.contains(KEY_VALUE_DELIMITER)
        ).map(
                cookie -> cookie.split(KEY_VALUE_DELIMITER)
        ).collect(Collectors.toMap(
                cookie -> cookie[0],
                cookie -> cookie[1]
        )));
    }

    public String getString() {
        return cookies.entrySet().stream().map(
                entry -> entry.getKey() + KEY_VALUE_DELIMITER + entry.getValue()
        ).collect(Collectors.joining(COOKIE_DELIMITER));
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(cookies.get(key));
    }

    private static class SingletonHolder {
        private static final Cookies EMPTY_INSTANCE = new Cookies(emptyMap());
    }
}
