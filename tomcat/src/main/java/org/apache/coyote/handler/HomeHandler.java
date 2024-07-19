package org.apache.coyote.handler;

import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.request.request_line.HttpMethod;
import org.apache.coyote.response.HttpResponse;

public final class HomeHandler implements HttpHandler {
    private HomeHandler() {}

    public static HomeHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean support(HttpRequest request) {
        return request.matchMethod(HttpMethod.GET)
                && request.path().equals("/");
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        return SingletonHolder.response;
    }

    private static class SingletonHolder {
        private static final HomeHandler INSTANCE = new HomeHandler();
        private static final HttpResponse response = HttpResponse.redirect("/index.html");
    }
}
