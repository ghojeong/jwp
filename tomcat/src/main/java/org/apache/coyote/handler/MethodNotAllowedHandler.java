package org.apache.coyote.handler;

import org.apache.coyote.common.ContentType;
import org.apache.coyote.common.HttpBody;
import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.response.HttpResponse;
import org.apache.coyote.response.StatusLine;

import static org.apache.coyote.response.HttpStatus.METHOD_NOT_ALLOWED;

public final class MethodNotAllowedHandler implements HttpHandler {
    private MethodNotAllowedHandler() {}

    public static MethodNotAllowedHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean support(HttpRequest request) {
        return false;
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        final String lineSeparator = "\r\n\r\n";
        final String body = METHOD_NOT_ALLOWED.getString()
                + lineSeparator
                + request.requestLine();
        return new HttpResponse(
                new HttpBody(ContentType.TEXT_PLAIN, body),
                StatusLine.methodNotAllowed()
        );
    }

    private static class SingletonHolder {
        private static final MethodNotAllowedHandler INSTANCE = new MethodNotAllowedHandler();
    }
}
