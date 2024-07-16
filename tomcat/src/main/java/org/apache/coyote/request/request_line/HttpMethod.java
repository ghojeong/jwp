package org.apache.coyote.request.request_line;

public enum HttpMethod {
    GET, POST, PUT, DELETE, HEAD, TRACE, OPTIONS, PATCH;

    public static HttpMethod from(String method) {
        return valueOf(method.toUpperCase());
    }
}
