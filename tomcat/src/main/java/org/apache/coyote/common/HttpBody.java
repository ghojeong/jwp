package org.apache.coyote.common;

public class HttpBody {
    private final ContentType contentType;
    private final byte[] body;

    public HttpBody(ContentType contentType, byte[] body) {
        this.contentType = contentType;
        this.body = body;
    }

    public HttpBody(ContentType contentType, String body) {
        this(contentType, body.getBytes());
    }

    public static HttpBody emptyInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String contentLength() {
        return String.valueOf(body.length);
    }

    public String contentType() {
        return contentType.toString();
    }

    public byte[] getBytes() {
        return body;
    }

    @Override
    public String toString() {
        return new String(body);
    }

    private static class SingletonHolder {
        private static final HttpBody INSTANCE = new HttpBody(ContentType.TEXT_PLAIN, "");
    }
}
