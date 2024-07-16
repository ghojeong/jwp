package org.apache.coyote.common;

import org.apache.coyote.exception.UnsupportedProtocolException;

import java.util.Arrays;

public enum HttpProtocol {
    HTTP_11("HTTP/1.1"),
    HTTP_1("HTTP/1.0"),
    HTTP_2("HTTP/2.0"),
    HTTP_3("HTTP/3.0");
    private final String name;

    HttpProtocol(String name) {
        this.name = name;
    }

    public static HttpProtocol from(String name) {
        return Arrays.stream(values()).filter(protocol -> protocol.matches(name))
                .findFirst().orElseThrow(() -> new UnsupportedProtocolException(name));
    }

    private boolean matches(String name) {
        return this.name.equals(name);
    }

    public String getString() {
        return name;
    }
}
