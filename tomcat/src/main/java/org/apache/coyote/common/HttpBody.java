package org.apache.coyote.common;

public class HttpBody {
    private final ContentType contentType;
    private final CharSet charSet = CharSet.UTF8;
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
        return contentType.getString() + charSet.getString();
    }

    public byte[] getBytes() {
        return body;
    }

    public String getString() {
        return new String(body);
    }

    private static class SingletonHolder {
        private static final HttpBody INSTANCE = new HttpBody(ContentType.TEXT_PLAIN, "");
    }
}
