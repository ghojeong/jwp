package org.apache.coyote.handler;

import org.apache.catalina.session.Session;
import org.apache.coyote.exception.UnsupportedHandlerException;
import org.apache.coyote.request.HttpRequest;
import org.apache.coyote.response.HttpResponse;

import java.util.LinkedList;

public final class HandlerManager {
    private static final LinkedList<HttpHandler> handlers = new LinkedList<>() {{
        add(HomeHandler.getInstance());
        add(FileHandler.getInstance());
    }};

    private HandlerManager() {}

    public static HandlerManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addHandler(HttpHandler handler) {
        handlers.addFirst(handler);
    }

    public byte[] handle(HttpRequest request) {
        final HttpResponse response = handlers.stream().filter(
                handler -> handler.support(request)
        ).findFirst().orElseThrow(
                () -> new UnsupportedHandlerException(request)
        ).handle(request);
        request.getSession().map(
                Session::getId
        ).ifPresent(response::setSession);
        return response.getBytes();
    }

    private static class SingletonHolder {
        private static final HandlerManager INSTANCE = new HandlerManager();
    }
}
