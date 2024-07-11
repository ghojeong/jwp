package org.apache.coyote.common;

public class HttpBody {
    private final String body;

    public HttpBody(String body) {
        this.body = body;
    }

    public static HttpBody emptyInstance() {
        return SingletonHolder.INSTANCE;
    }

    public byte[] getBytes() {
        return body.getBytes();
    }

    @Override
    public String toString() {
        return body;
    }

    private static class SingletonHolder {
        private static final HttpBody INSTANCE = new HttpBody("");
    }
}
