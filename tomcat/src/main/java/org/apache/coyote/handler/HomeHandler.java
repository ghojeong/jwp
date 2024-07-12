package org.apache.coyote.handler;

import org.apache.coyote.common.HttpBody;
import org.apache.coyote.common.HttpHeaders;
import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.response.HttpResponse;

public final class HomeHandler implements HttpHandler {
    private HomeHandler() {}

    public static HomeHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean support(HttpRequest request) {
        return true;
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        final String body = "Hello world!";
        return new HttpResponse(
                HttpHeaders.of(
                        "Content-Type: text/html;charset=utf-8 ",
                        "Content-Length: " + body.length()
                ),
                new HttpBody(body)
        );
    }

    private static class SingletonHolder {
        private static final HomeHandler INSTANCE = new HomeHandler();
    }
}
